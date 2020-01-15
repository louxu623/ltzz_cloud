package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.MACDGoldenCrossInfoDao;
import com.ltzz.modules.base.dao.Tweet7dTopDao;
import com.ltzz.modules.base.entity.Follow7dTop50;
import com.ltzz.modules.base.entity.MACDGoldenCrossInfo;
import com.ltzz.modules.base.entity.Tweet7dTop50;
import com.ltzz.modules.base.service.MACDGoldenCrossService;
import com.ltzz.modules.base.service.Tweet7dTopService;
import com.ltzz.util.StockUtil;
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


@Service("tweet7dTopService")
public class Tweet7dTopServiceImpl extends ServiceImpl<Tweet7dTopDao, Tweet7dTop50> implements Tweet7dTopService {
    private static Log log = LogFactory.getLog(Tweet7dTopServiceImpl.class);

    @Override
    public List<Tweet7dTop50> getTweet7dTop50(String traceId, List<Tweet7dTop50> res) {
        if (null == res) {
            res = new ArrayList<Tweet7dTop50>();
        }
        //  首先查数据库
        String currentDateStr = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        res = this.list(new QueryWrapper<Tweet7dTop50>().eq("trigger_date", currentDateStr));
        if (!res.isEmpty()) {
            return res;
        }
        long currentTime = System.currentTimeMillis();
        String reqUrl = StockUtil.getTweetTopUrl(currentTime);
        log.info(traceId + ", reqUrl = " + reqUrl);
        try {
            String reqResult = "";
            //  此处需要先请求主站，获取cookie信息，否则后面会取不到数据
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpGet get = new HttpGet(StockUtil.getXueQiuMainUrl());
            CloseableHttpResponse executeRes = httpClient.execute(get);
            //  TODO  更新周讨论数排行
            get = new HttpGet(reqUrl);
            executeRes = httpClient.execute(get);
            if (executeRes.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                reqResult = EntityUtils.toString(executeRes.getEntity(), "utf-8");
                JSONArray items = JSON.parseObject(reqResult).getJSONObject("data").getJSONArray("list");
                res = new ArrayList<Tweet7dTop50>(items.size());
                for (int i = 0; i < items.size(); i++) {
                    JSONObject itemStr = JSON.parseObject(items.get(i).toString());
                    Tweet7dTop50 info = JSON.parseObject(items.get(i).toString(), Tweet7dTop50.class);
                    try {
                        info.setCode(info.getSymbol().substring(2, info.getSymbol().length()));
                        info.setCurrentPrice(itemStr.getDouble("current"));
                        info.setTriggerDate(DateUtils.format(currentTime, DateUtils.DATETIMEPATTERN));
                        info.setCreateTime(currentTime);
                        res.add(info);
                    } catch (Exception e) {
                        log.error("接口返回数据解析失败, url = " + reqUrl + ", res = " + items.get(i).toString());
                    }
                }
                this.saveBatch(res);
            } else {
                log.info(traceId + ", 获取第三方数据出错。");
            }
        } catch (Exception e) {
            log.info(traceId + ", 获取第三方数据出错。");
        }
        return res;
    }

    @Override
    public Tweet7dTop50 getTweet7dBySymbol(String traceId, String symbol) {
        return null;
    }

    @Override
    public Tweet7dTop50 getTweetBySymbol(String traceId, String symbol) {
        return null;
    }
}
