package com.ltzz.util;

import org.springblade.common.constant.Constant;
import org.springblade.common.em.MarketType;

/**
 * @author luox
 * @description 基础工具类
 * @date 2019/10/28
 */
public class StockUtil {
    /**
     * add method description.
     *
     * @param sType    市场类型，参见：MarketType.class
     * @param page     当前页
     * @param pageType 接口分页类型：每页返回条数,1(20条默认),2(40条),3(60条),4(80条)
     * @return         api路径
     * @author luox
     * @date 2019/10/28
     */
    public static String getBaseInfoUrl(int sType, int page, int pageType) {
        return MarketType.SH.getCode() == sType ? String.format(Constant.STOCK_BASE_INFO_URL_SH, page, pageType) : String.format(Constant.STOCK_BASE_INFO_URL_SZ, page, pageType);
    }

    public static String getMacdGoldenCrossInfoUrl(int page, int pageCount) {
        return String.format(Constant.MACD_GOLDEN_CROSS_INFO_URL, page, pageCount);
    }

    public static String getXueQiuMainUrl() {
        return "https://xueqiu.com/";
    }

    public static String getFollowTopUrl(long currentTimeMillis) {
        return String.format(Constant.FOLLOW_TOP_URL, currentTimeMillis);
    }
//    public static String getFollowUrl(int page, int pageSize, long currentTimeMillis) {
//        return String.format(Constant.FOLLOW_URL, page, pageSize, currentTimeMillis);
//    }
    public static String getTweetUrl(int page, int pageSize, long currentTimeMillis) {
//        return String.format(Constant.TWEET_URL.replace("pageValue", page + "").replace("pageSizeValue", pageSize + ""), currentTimeMillis);
        return String.format(Constant.TWEET_URL, page, pageSize, currentTimeMillis);
    }
    public static String getTweetTopUrl(long currentTimeMillis) {
        return String.format(Constant.TWEET_TOP_URL, currentTimeMillis);
    }

    public static void main(String[] args) {
        System.out.println(getTweetUrl(1, 100, 103938566548L));
    }
}
