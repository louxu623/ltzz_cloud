package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.Follow7dTopDao;
import com.ltzz.modules.base.dao.ImportantIndexDataDao;
import com.ltzz.modules.base.entity.*;
import com.ltzz.modules.base.service.Follow7dTopService;
import com.ltzz.modules.base.service.ImportantIndexDataService;
import com.ltzz.util.StockUtil;
import com.vpiaotong.openapi.util.HttpUtils;
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
import org.springblade.common.constant.Constant;
import org.springblade.common.tool.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("importantIndexDataService")
public class ImportantIndexDataServiceImpl extends ServiceImpl<ImportantIndexDataDao, ImportantIndexData> implements ImportantIndexDataService {
    private static Log log = LogFactory.getLog(ImportantIndexDataServiceImpl.class);

    @Override
    public ImportantIndexData printIndexDataDo(String traceId, StockBaseInfo stock, String queryDate) {
        ImportantIndexData resultDo = null;
        String requestUrl = Constant.BAIDU_API_IMPORTANT_DATA + stock.getSymbol();
        String urlResult = HttpUtils.get(requestUrl);
        JSONObject resultObject = JSON.parseObject(urlResult);
        if (resultObject == null || resultObject.get("mashData") == null) {
            log.info(traceId + ", 当前股票不存在：url = " + requestUrl);
            return resultDo;
        }
        try {
            JSONArray dataArray = resultObject.getJSONArray("mashData");
            if (!dataArray.isEmpty()) {
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject dataObject = JSON.parseObject(dataArray.get(i).toString());
                    if (dataObject != null) {
                        if (dataObject.getString("date") != null && queryDate.equals(dataObject.getString("date"))) {
                            ImportantIndexData data = this.getOne(new QueryWrapper<ImportantIndexData>().eq("biz_date", queryDate).eq("stock_code", stock.getSymbol()));
                            if (data == null) {
                                resultDo = new ImportantIndexData();
                                // 基本信息
                                resultDo.setStockCode(stock.getSymbol());
                                resultDo.setBizDate(Long.parseLong(queryDate));
                                // K线
                                JSONObject klineObject = dataObject.getJSONObject("kline");
                                if (klineObject != null) {
                                    resultDo.setOpen(klineObject.getDouble("open"));
                                    resultDo.setHigh(klineObject.getDouble("high"));
                                    resultDo.setLow(klineObject.getDouble("low"));
                                    resultDo.setClose(klineObject.getDouble("close"));
                                    resultDo.setVolume(klineObject.getDouble("volume"));
                                    resultDo.setAmount(klineObject.getDouble("amount"));
                                    resultDo.setPreClose(klineObject.getDouble("preClose"));
                                }
                                // ma5
                                JSONObject ma5Object = dataObject.getJSONObject("ma5");
                                if (ma5Object != null) {
                                    resultDo.setMa5(ma5Object.getDouble("avgPrice"));
                                }
                                // ma10
                                JSONObject ma10Object = dataObject.getJSONObject("ma10");
                                if (ma10Object != null) {
                                    resultDo.setMa10(ma10Object.getDouble("avgPrice"));
                                }
                                // ma20
                                JSONObject ma20Object = dataObject.getJSONObject("ma20");
                                if (ma20Object != null) {
                                    resultDo.setMa20(ma20Object.getDouble("avgPrice"));
                                }
                                // MACD
                                JSONObject macdObject = dataObject.getJSONObject("macd");
                                if (macdObject != null) {
                                    resultDo.setDiff(macdObject.getDouble("diff"));
                                    resultDo.setDea(macdObject.getDouble("dea"));
                                    resultDo.setMacd(macdObject.getDouble("macd"));
                                }
                                // KDJ
                                JSONObject kdjObject = dataObject.getJSONObject("kdj");
                                if (macdObject != null) {
                                    resultDo.setK(kdjObject.getDouble("k"));
                                    resultDo.setD(kdjObject.getDouble("d"));
                                    resultDo.setJ(kdjObject.getDouble("j"));
                                }
                                resultDo.setCreateTime(new Date().getTime());
                                this.save(resultDo);
                            } else {
                                log.info(traceId + ", 当前股票的指标信息已经存在！ stockCode = " + data.getStockCode() + ", bizDate = " + data.getBizDate());
                            }
                        }
                    }
                }
            } else {
                log.info(traceId + ", 当前股票数据不存在：url = " + requestUrl);
            }
        } catch (Exception e) {
            log.error(traceId + ", 获取股票【" + stock.getName() + "】数据出错，url = " + requestUrl, e);
        }
        return resultDo;
    }

    @Override
    public GoodStocks getGoodStocks(String traceId, String queryDate) {
        GoodStocks result = new GoodStocks();
        //  首先查询一下指定日期的数据是否存在，不存在则不处理
        ImportantIndexData entityByBizDate = this.getOne(new QueryWrapper<ImportantIndexData>().eq("biz_date", queryDate).last(" limit 1"));
        if (null == entityByBizDate) {
            log.info(traceId + ", 指定日期的数据不存在，不处理。");
            return result;
        }
        //  获取前一个交易日的日期
        ImportantIndexData entityByBeforeDate = this.getOne(new QueryWrapper<ImportantIndexData>().le("biz_date", queryDate).orderByDesc("biz_date"));
        if (null == entityByBeforeDate) {
            log.info(traceId + ", 指定日期前一个交易日的数据不存在，不处理。");
            return result;
        }
        Integer analysisIndexDateCurrent = entityByBizDate.getBizDate().intValue();
        Integer analysisIndexDateBefore = entityByBeforeDate.getBizDate().intValue();

        List<ImportantIndexData> indexInfos = new ArrayList<ImportantIndexData>();
        List<ImportantIndexData> beforeIndexInfos = this.list(new QueryWrapper<ImportantIndexData>().eq("biz_date", analysisIndexDateBefore));
        log.info(traceId + ", beforeIndexInfos size = " + beforeIndexInfos.size());
        indexInfos.addAll(beforeIndexInfos);
        List<ImportantIndexData> currentIndexInfos = this.list(new QueryWrapper<ImportantIndexData>().eq("biz_date", analysisIndexDateCurrent));
        log.info(traceId + ", currentIndexInfos size = " + currentIndexInfos.size());
        indexInfos.addAll(currentIndexInfos);

        Map<String, ImportantIndexData> analysisIndexMap = new HashMap<String, ImportantIndexData>();
        for (ImportantIndexData index : indexInfos) {
            analysisIndexMap.put(index.getStockCode() + "-" + index.getBizDate(), index);
        }

        //  MACD金叉
        List<StockAnalysis> goodStocks = new ArrayList<StockAnalysis>();
        //  MACD金叉 + KDJ金叉
        List<ImportantIndexData> superGoodStocks = new ArrayList<ImportantIndexData>();
        //  MACD金叉 + KDJ金叉 + MA金叉
        List<ImportantIndexData> superSupperGoodStocks = new ArrayList<ImportantIndexData>();
        for (ImportantIndexData index : indexInfos) {
            if (analysisIndexDateCurrent.equals(index.getBizDate())) {
                //  当前MACD
                Double curentDiff = analysisIndexMap.get(index.getStockCode()).getDiff();
                Double currentDea = analysisIndexMap.get(index.getStockCode()).getDea();
                if (analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore) != null) {
                    //  昨日MACD
                    Double beforeDiff = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getDiff();
                    Double beforeDea = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getDea();
                    if (currentDea > 0 && beforeDiff < beforeDea && curentDiff > currentDea) {
                        StockAnalysis analysisStock = new StockAnalysis(analysisIndexDateCurrent,
                                index.getStockCode(), (byte) 1, index.getClose(), index.getPreClose(), index.getOpen(),
                                (byte) 0, (byte) 0, 0, (int) (System.currentTimeMillis() / 1000), 0);
                        goodStocks.add(analysisStock);
                        //  当前KDJ
                        Double currentK = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateCurrent).getK();
                        Double currentD = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateCurrent).getD();
                        //  昨日KDJ
                        Double beforeK = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getK();
                        Double beforeD = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getD();
                        if (currentK > 0 && beforeK < beforeD && currentK > currentD) {
                            superGoodStocks.add(index);
                            //  当前MA5、MA10
                            Double currentMA5 = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateCurrent).getMa5();
                            Double currentMA10 = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateCurrent).getMa10();
                            //  昨日MA5、MA10
                            Double beforeMA5 = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getMa5();
                            Double beforeMA10 = analysisIndexMap.get(index.getStockCode() + "-" + analysisIndexDateBefore).getMa10();
                            if (beforeMA5 < beforeMA10 && currentMA5 > currentMA10) {
                                superSupperGoodStocks.add(index);
                            }
                        }
                    }
                }
            }
        }
        log.info(traceId + ", goodStocks.size = " + goodStocks.size());
        if (goodStocks.size() > 0) {
            result.setMacdGlodStocks(goodStocks);
        }
        return result;
    }
}
