package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.MACDGoldenCrossInfoDao;
import com.ltzz.modules.base.entity.Follow7dTop50;
import com.ltzz.modules.base.entity.MACDGoldenCrossInfo;
import com.ltzz.modules.base.entity.Tweet7dTop50;
import com.ltzz.modules.base.service.MACDGoldenCrossService;
import com.ltzz.util.StockUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springblade.common.tool.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("macdGoldenCrossService")
public class MACDGoldenCrossServiceImpl extends ServiceImpl<MACDGoldenCrossInfoDao, MACDGoldenCrossInfo> implements MACDGoldenCrossService {
    private static Log log = LogFactory.getLog(MACDGoldenCrossServiceImpl.class);

    @Override
    public List<MACDGoldenCrossInfo> getAndUpdateTodayAllMACDGoldenCrossStock(String traceId, String queryDate, List<MACDGoldenCrossInfo> res) {
        if (null == res) {
            res = new ArrayList<MACDGoldenCrossInfo>();
        }
        //  首先查数据库
//        String currentDateStr = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        res = this.list(new QueryWrapper<MACDGoldenCrossInfo>().eq("trigger_date", queryDate));
        if (!res.isEmpty()) {
            return res;
        }
        int page = 0;
        int pageCount = 100;
        String reqUrl = StockUtil.getMacdGoldenCrossInfoUrl(page, pageCount);
        log.info(traceId + ", reqUrl = " + reqUrl);
        try {
            String reqResult = "";
            //  此处需要先请求主站，获取cookie信息，否则后面会取不到数据
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpGet get = new HttpGet(StockUtil.getXueQiuMainUrl());
            CloseableHttpResponse executeRes = httpClient.execute(get);
            //  获取并解析今日MACD金叉股
            get = new HttpGet(reqUrl);
            executeRes = httpClient.execute(get);
            if (executeRes.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                reqResult = EntityUtils.toString(executeRes.getEntity(), "utf-8");
            } else {
                log.info(traceId + ", 获取第三方数据出错。");
                return res;
            }

            JSONObject resObject = JSON.parseObject(reqResult);
            JSONArray items = JSON.parseArray(resObject.get("items").toString());
            for (int i = 0; i < items.size(); i++) {
                JSONObject itemStr = JSON.parseObject(items.get(i).toString());
                MACDGoldenCrossInfo info = JSON.parseObject(items.get(i).toString(), MACDGoldenCrossInfo.class);
                if (!queryDate.equals(DateUtils.format(info.getTriggerTime(), DateUtils.DATETIMEPATTERN))) {
                    log.info(traceId + ", 非今日最新数据。");
                    continue;
                }
//                if (!info.isNew() || info.getTriggerTime() < DateUtils.addDateHours(DateUtils.getTodayStart(), 15).getTime()) {
//                    log.info(traceId + ", 非今日最新数据。");
//                    continue;
//                }
                try {
                    info.setCode(info.getSymbol().substring(2, info.getSymbol().length()));
                    info.setDescription(itemStr.getString("desc"));
                    info.setTriggerDate(queryDate);
                    info.setCreateTime(System.currentTimeMillis());
                    res.add(info);
                } catch (Exception e) {
                    log.error("接口返回数据解析失败, url = " + reqUrl + ", res = " + items.get(i).toString());
                }
            }
            if (res.isEmpty()) {
                log.info(traceId + ", 获取第三方数据出错。");
                return res;
            }
            this.saveOrUpdateBatch(res);
        } catch (Exception e) {
            log.info(traceId + ", 获取第三方数据出错。");
        }
        return res;
    }
}
