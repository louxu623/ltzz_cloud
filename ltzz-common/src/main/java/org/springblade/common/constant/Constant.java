/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package org.springblade.common.constant;

/**
 * 常量
 */
public class Constant {
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * 有效
     */
    public static final Integer ENABLE_VALID = 1;
    /**
     * 无效
     */
    public static final Integer ENABLE_INVALID = 0;

    public static final String STOCK_API_KEY = "4de4d273aefb14fba114ee8569dcff37";
    public static final String STOCK_BASE_INFO_URL_SH = "http://web.juhe.cn:8080/finance/stock/shall?stock=a&key=" + STOCK_API_KEY + "&page=%d&type=%d";
    public static final String STOCK_BASE_INFO_URL_SZ = "http://web.juhe.cn:8080/finance/stock/szall?stock=a&key=" + STOCK_API_KEY + "&page=%d&type=%d";
    public static final String MACD_GOLDEN_CROSS_INFO_URL = "https://xueqiu.com/snowmart/push/stocks.json?product_id=6&page=%d&count=%d";
    //    public static final String FOLLOW_URL = "https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=follow&order=desc&page=d%&size=d%&only_count=0&current=&pct=&follow=0_99999999&_=d%";
    public static final String TWEET_URL = "https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=tweet&order=desc&page=%d&size=%d&only_count=0&current=&pct=&follow=0_99999999&_=%d";
    public static final String FOLLOW_TOP_URL = "https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=follow7d&order=desc&page=1&size=50&only_count=0&current=&pct=&follow7d=0_9999999&_=%d";
    public static final String TWEET_TOP_URL = "https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=tweet7d&order=desc&page=1&size=50&only_count=0&current=&pct=&follow7d=0_9999999&_=%d";
    //    https://xueqiu.com/service/screener/screen?category=CN&exchange=sh_sz&areacode=&indcode=&order_by=follow&order=desc&page=1&size=30&only_count=0&current=&pct=&follow=0_1092731&_=1573699213579
    public static final String BAIDU_API_IMPORTANT_DATA = "https://gupiao.baidu.com/api/stocks/stockdaybar?from=pc&os_ver=1&cuid=xxx&vv=100&format=json&step=3&start=&count=30&fq_type=no&stock_code=";
}
