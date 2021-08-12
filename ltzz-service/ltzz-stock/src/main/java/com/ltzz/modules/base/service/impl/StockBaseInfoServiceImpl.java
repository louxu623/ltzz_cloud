package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.StockBaseInfoDao;
import com.ltzz.modules.base.entity.StockBaseInfo;
import com.ltzz.modules.base.service.StockBaseInfoService;
import com.ltzz.util.StockUtil;
import com.vpiaotong.openapi.util.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Service("stockBaseInfoService")
public class StockBaseInfoServiceImpl extends ServiceImpl<StockBaseInfoDao, StockBaseInfo> implements StockBaseInfoService {
    private static Log log = LogFactory.getLog(StockBaseInfoServiceImpl.class);

    @Override
    public void getBaseInfo(List<StockBaseInfo> stocks, int stype, int page, int type, int typeStep) {
        int totalCount = 0;
        String reqUrl = StockUtil.getBaseInfoUrl(stype, page, type);
        String reqResult = HttpUtils.get(reqUrl);
        JSONObject resObject = JSON.parseObject(reqResult);
        if ("0".equals(resObject.get("error_code").toString())) {
            JSONObject res = resObject.getJSONObject("result");
            totalCount = Integer.parseInt(res.get("totalCount").toString());
            JSONArray resutlDatas = res.getJSONArray("data");
            for (int i = 0; i < resutlDatas.size(); i++) {
                try {
                    StockBaseInfo stockObj = JSON.parseObject(resutlDatas.get(i).toString(), StockBaseInfo.class);
                    stockObj.setCreateTime(System.currentTimeMillis());
                    stocks.add(stockObj);
                } catch (Exception e) {
                    log.error("接口返回数据解析失败, url = " + reqUrl + ", res = " + resutlDatas.get(i).toString());
                }
            }
        } else {
            log.info("调用合众数据接口返回失败, url = " + reqUrl);
        }
        while (totalCount > page * type * typeStep) {  //  超过1页，则分页查询剩下的数据
            page++;
            reqUrl = StockUtil.getBaseInfoUrl(stype, page, type);
            getBaseInfo(stocks, stype, page, type, typeStep);
            return;
        }
    }

    @Override
    public void updateStockSort(String traceId, StockBaseInfo stock) {
        String reqUrl = "https://guba.eastmoney.com/interface/GetData.aspx";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqUrl);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("param", "code=" + stock.getCode()));
        nvps.add(new BasicNameValuePair("path", "webarticlelist/api/guba/gubainfo"));
        nvps.add(new BasicNameValuePair("env", "2"));
        //  设置参数到请求对象中
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.info(traceId, e);
        }
        //  设置header信息
        String refererValue = "https://guba.eastmoney.com/list," + stock.getCode() + ".html";
        httpPost.setHeader("Referer", refererValue);

        //  执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            //  获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            //  按指定编码转换结果实体为String类型
//                String body = EntityUtils.toString(entity, "utf-8");
                JSONObject res = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                stock.setSort(res.getInteger("popular_rank"));
                stock.setChangeSort(res.getInteger("popular_change"));
                updateById(stock);
            }
            EntityUtils.consume(entity);
            //  释放链接
            response.close();
        } catch (IOException e) {
            log.info(traceId, e);
        }
    }
}
