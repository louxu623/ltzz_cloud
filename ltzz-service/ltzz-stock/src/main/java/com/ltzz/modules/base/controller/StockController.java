package com.ltzz.modules.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ltzz.modules.app.entity.vo.LoginVo;
import com.ltzz.modules.base.entity.*;
import com.ltzz.modules.base.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springblade.common.em.MarketType;
import org.springblade.common.tool.DateUtils;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author luox
 * @description 股票数据处理
 * @date 2019/10/25
 */
@RestController
@RequestMapping("/stock")
@Api(value = "股票数据获取接口", tags = "股票数据获取接口")
@AllArgsConstructor
public class StockController {
    private static Log log = LogFactory.getLog(StockController.class);
    @Autowired
    StockBaseInfoService stockBaseInfoService;
    @Autowired
    ImportantIndexDataService importantIndexDataService;
    @Autowired
    MACDGoldenCrossService macdGoldenCrossService;
    @Autowired
    Follow7dTopService follow7dTopService;
    @Autowired
    Tweet7dTopService tweet7dTopService;
    @Autowired
    LtFollowTweetService ltFollowTweetService;

    /**
     * 登录
     */
    @GetMapping("info")
    @ApiOperation("获取token")
    public R<LoginVo> login(@ApiParam(name = "stockCode", value = "股票代码", required = true) @RequestParam String stockCode) {
        return R.data(null);
    }

    /**
     * 更新沪深两市全量股票基本信息
     */
    @GetMapping("updateAllStockInfo")
    @ApiOperation("更新沪深两市全量股票基本信息")
    public R<Boolean> updateAllStockInfo() {
        boolean res = false;
        List<StockBaseInfo> stocks = new ArrayList<StockBaseInfo>();
        int type = 4;
        int typeStep = 20;
        int page = 1;
        //  沪股
        stockBaseInfoService.getBaseInfo(stocks, MarketType.SH.getCode(), page, type, typeStep);
        //  深股
        stockBaseInfoService.getBaseInfo(stocks, MarketType.SZ.getCode(), page, type, typeStep);
        //  写数据
        if (stocks.size() > 0) {
            res = stockBaseInfoService.saveOrUpdateBatch(stocks);
        }
        return R.data(res);
    }

    /**
     * 更新今日沪深两市MACD金叉股票信息
     */
    @GetMapping("getAndUpdateTodayAllMACDGoldenCrossStock")
    @ApiOperation("获取并更新今日所有MACD金叉股票信息")
    public R<List<MACDGoldenCrossInfo>> getAndUpdateTodayAllMACDGoldenCrossStock(@ApiParam(name = "queryDate", value = "数据日期", required = false) @RequestParam(name = "queryDate", required = false) String queryDate) {
        String traceId = UUID.randomUUID().toString();
        if (StringUtils.isBlank(queryDate)) {
            queryDate = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        }
        List<MACDGoldenCrossInfo> res = new ArrayList<MACDGoldenCrossInfo>();
        res = macdGoldenCrossService.getAndUpdateTodayAllMACDGoldenCrossStock(traceId, queryDate, res);
        return R.data(res);
    }

    /**
     * 获取一周新增关注数TOP50
     */
    @GetMapping("getFollow7dTop50")
    @ApiOperation("获取一周新增关注数TOP50")
    public R<List<Follow7dTop50>> getFollow7dTop50() {
        String traceId = UUID.randomUUID().toString();
        List<Follow7dTop50> res = new ArrayList<Follow7dTop50>();
        res = follow7dTopService.getFollow7dTop50(traceId, res);
        return R.data(res);
    }

    /**
     * 获取一周新增讨论数TOP50
     */
    @GetMapping("getTweet7dTop50")
    @ApiOperation("获取一周新增讨论数TOP50")
    public R<List<Tweet7dTop50>> getTweet7dTop50() {
        String traceId = UUID.randomUUID().toString();
        List<Tweet7dTop50> res = new ArrayList<Tweet7dTop50>();
        res = tweet7dTopService.getTweet7dTop50(traceId, res);
        return R.data(res);
    }

    /**
     * 获取指定股票一周新增讨论数
     */
    @GetMapping("getTweet7dBySymbol")
    @ApiOperation("获取指定股票一周新增讨论数")
    public R<Tweet7dTop50> getTweet7dBySymbol(@ApiParam(name = "symbol", value = "股票代码", required = true) @RequestParam(name = "symbol", required = false) String symbol) {
        String traceId = UUID.randomUUID().toString();
        Tweet7dTop50 res = tweet7dTopService.getTweet7dBySymbol(traceId, symbol);
        return R.data(res);
    }

    /**
     * 获取指定股票一周新增关注数
     */
    @GetMapping("getFollow7dBySymbol")
    @ApiOperation("获取指定股票一周新增关注数")
    public R<Follow7dTop50> getFollow7dBySymbol(@ApiParam(name = "symbol", value = "股票代码", required = true) @RequestParam(name = "symbol", required = false) String symbol) {
        String traceId = UUID.randomUUID().toString();
        Follow7dTop50 res = follow7dTopService.getFollow7dBySymbol(traceId, symbol);
        return R.data(res);
    }

    /**
     * 获取指定股票累计讨论数、关注数
     */
    @GetMapping("getTweetBySymbol")
    @ApiOperation("获取指定股票累计讨论数")
    public R<LtFollowTweet> getTweetBySymbol(@ApiParam(name = "symbol", value = "股票代码", required = true) @RequestParam(name = "symbol", required = false) String symbol) {
        String traceId = UUID.randomUUID().toString();
        LtFollowTweet res = ltFollowTweetService.getDataBySymbol(traceId, symbol);
        return R.data(res);
    }

    /**
     * 更新讨论数、关注数
     */
    @GetMapping("queryOrUpdateData")
    @ApiOperation("更新讨论数、关注数")
    public R<Boolean> queryOrUpdateData() {
        String traceId = UUID.randomUUID().toString();
        ltFollowTweetService.queryOrUpdateData(traceId);
        return R.data(true);
    }


    /**
     * 更新指定日期的所有股票数据
     */
    @GetMapping("updateImportantIndexData")
    @ApiOperation("更新指定日期的所有股票数据")
    public R<Boolean> updateImportantIndexData(@ApiParam(name = "date", value = "日期", required = false) @RequestParam(name = "date", required = false) String date) {
        String traceId = UUID.randomUUID().toString();
        if (StringUtils.isBlank(date)) {
            //  默认为当天日期
            date = DateUtils.format(new Date(), "yyyyMMdd");
        }
        List<StockBaseInfo> baseInfos = stockBaseInfoService.list(new QueryWrapper<StockBaseInfo>().like("symbol", "%sh%"));
        StockBaseInfo temp = new StockBaseInfo();
        for (StockBaseInfo stock : baseInfos) {
            importantIndexDataService.printIndexDataDo(traceId, stock, date);
        }
        return R.data(true);
    }

    /**
     * 获取指定日期所有MACD股票
     */
    @GetMapping("getMACDStocks")
    @ApiOperation("获取指定日期所有MACD股票")
    public R<GoodStocks> getMACDStocks(@ApiParam(name = "date", value = "日期", required = false) @RequestParam(name = "date", required = false) String date) {
        String traceId = UUID.randomUUID().toString();
        if (StringUtils.isBlank(date)) {
            //  默认为当天日期
            date = DateUtils.format(new Date(), "yyyyMMdd");
        }
        GoodStocks result = importantIndexDataService.getGoodStocks(traceId, date);
        return R.data(result);
    }

//    一周新增讨论数TOP50
//    https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=tweet7d&order=desc&page=1&size=50&only_count=0&current=&pct=&tweet7d=0_9999999&_=1572340434128

//    一周新增关注前TOP50：
//    https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=follow7d&order=desc&page=1&size=50&only_count=0&current=&pct=&follow7d=0_9999999&_=1572340355200

//    public static void main(String args[]) throws IOException {
//        CookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//        HttpGet get = new HttpGet("https://xueqiu.com/");
//        CloseableHttpResponse executeRes = httpClient.execute(get);
//        if (executeRes.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            List<Cookie> cookies = cookieStore.getCookies();
//        }
//
//        get = new HttpGet("https://xueqiu.com/snowmart/push/stocks.json?product_id=6&page=0&count=100");
//        executeRes = httpClient.execute(get);
//        if (executeRes.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String s = EntityUtils.toString(executeRes.getEntity(), "utf-8");
//            System.out.println(s);
//        }
//    }
}
