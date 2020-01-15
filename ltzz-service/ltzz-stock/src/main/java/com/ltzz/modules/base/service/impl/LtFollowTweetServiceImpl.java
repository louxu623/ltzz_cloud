package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.LtFollowTweetDao;
import com.ltzz.modules.base.entity.LtFollowTweet;
import com.ltzz.modules.base.service.LtFollowTweetService;
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
import org.springblade.common.tool.ListSortUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("ltFollowTweetService")
public class LtFollowTweetServiceImpl extends ServiceImpl<LtFollowTweetDao, LtFollowTweet> implements LtFollowTweetService {
    private static Log log = LogFactory.getLog(LtFollowTweetServiceImpl.class);
    private static int pageSize = 100;

    @Override
    public void queryOrUpdateData(String traceId) {
        //  首先查数据库
        String currentDateStr = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        int count = this.count(new QueryWrapper<LtFollowTweet>().eq("trigger_date", currentDateStr));
        if (count > 0) {
            log.info(traceId + ",今日数据已存在，不再处理。");
            return;
        }
        long currentTime = System.currentTimeMillis();
        try {
            String reqResult = "";
            //  此处需要先请求主站，获取cookie信息，否则后面会取不到数据
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpGet get = new HttpGet(StockUtil.getXueQiuMainUrl());
            CloseableHttpResponse executeRes = httpClient.execute(get);
            //  更新讨论数排行
            List<LtFollowTweet> ltTweets = new ArrayList<LtFollowTweet>();

            String reqUrl = StockUtil.getTweetUrl(1, pageSize, currentTime);
            log.info(traceId + ", reqUrl = " + reqUrl);
            int dataCount = getData(traceId, httpClient, get, executeRes, currentDateStr, reqUrl, ltTweets);
            int maxPage = 1;
            if (dataCount > pageSize) {
                maxPage = (dataCount / pageSize) + 1;          //  计算查询页数
            }

            for (int i = 0; i < maxPage; i++) {
                if (i == 0) {
                    continue;
                }
                if (i == (maxPage - 1)) {
                    pageSize = dataCount - (pageSize * i);
                }
                reqUrl = StockUtil.getTweetUrl(i + 1, pageSize, currentTime);
                getData(traceId, httpClient, get, executeRes, currentDateStr, reqUrl, ltTweets);
            }
            //  计算累计讨论数排行
            ListSortUtil.sort(ltTweets, "tweet");
            int sort = ltTweets.size();
            for (LtFollowTweet sortTweet : ltTweets) {
                sortTweet.setTweetRank(sort--);
            }
            //  计算累计关注数排行
            ListSortUtil.sort(ltTweets, "follow");
            sort = ltTweets.size();
            for (LtFollowTweet sortFollow : ltTweets) {
                sortFollow.setFollowRank(sort--);
            }
            //  计算新增关注数排行
            ListSortUtil.sort(ltTweets, "followNewRank");
            sort = ltTweets.size();
            for (LtFollowTweet sortFollowNew : ltTweets) {
                sortFollowNew.setFollowNewRank(sort--);
            }
            //  计算新增讨论数排行
            ListSortUtil.sort(ltTweets, "tweetNewRank");
            sort = ltTweets.size();
            for (LtFollowTweet sortTweetNew : ltTweets) {
                sortTweetNew.setTweetNewRank(sort--);
            }

            this.saveOrUpdateBatch(ltTweets);
        } catch (Exception e) {
            log.info(traceId + ", 获取第三方数据出错。 msg = " + e.getMessage());
        }
    }

    @Override
    public LtFollowTweet getDataBySymbol(String traceId, String symbol) {
        return this.getById(symbol);
    }

    private int getData(String traceId, CloseableHttpClient httpClient, HttpGet httpGet, CloseableHttpResponse executeRes, String currentDateStr, String url, List<LtFollowTweet> ltTweets) throws Exception {
        long time = System.currentTimeMillis();
        int dataCount = 0;
        httpGet = new HttpGet(url);
        executeRes = httpClient.execute(httpGet);
        if (executeRes.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String reqResult = EntityUtils.toString(executeRes.getEntity(), "utf-8");
            JSONObject reqRes = JSON.parseObject(reqResult);
            if (0 != reqRes.getInteger("error_code")) {
                log.info(traceId + ", 接口请求出错。msg = " + reqRes.getString("error_description"));
                return 0;
            }
            JSONObject resDatas = reqRes.getJSONObject("data");
            dataCount = resDatas.getInteger("count");
            JSONArray items = resDatas.getJSONArray("list");
            for (int i = 0; i < items.size(); i++) {
                JSONObject itemStr = JSON.parseObject(items.get(i).toString());
                LtFollowTweet info = JSON.parseObject(items.get(i).toString(), LtFollowTweet.class);
                try {
                    info.setCode(info.getSymbol().substring(2, info.getSymbol().length()));
                    info.setCurrentPrice(itemStr.getDouble("current"));
                    info.setTriggerDate(currentDateStr);

                    //  获取昨日数据
                    LtFollowTweet oldData = this.getOne(new QueryWrapper<LtFollowTweet>().eq("symbol", info.getSymbol()));
                    if (null != oldData) {
                        info.setUpdateTime(time);
                        info.setFollowNew(info.getFollow() - oldData.getFollow());
                        info.setTweetNew(info.getTweet() - oldData.getTweet());
                    } else {
                        info.setCreateTime(time);
                    }

                    ltTweets.add(info);
                } catch (Exception e) {
                    log.error("接口返回数据解析失败, url = " + url + ", res = " + items.get(i).toString());
                }
            }
        }
        return dataCount;
    }
}
