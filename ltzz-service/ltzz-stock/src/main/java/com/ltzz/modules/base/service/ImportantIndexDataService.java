package com.ltzz.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.GoodStocks;
import com.ltzz.modules.base.entity.ImportantIndexData;
import com.ltzz.modules.base.entity.StockBaseInfo;

public interface ImportantIndexDataService extends IService<ImportantIndexData> {
    /**
     * 解析重要指标信息
     * @param traceId
     * @param stock
     * @param queryDate
     * @return
     */
    ImportantIndexData printIndexDataDo(String traceId, StockBaseInfo stock, String queryDate);

    /**
     * 从所有股票中筛选出指定日期形成金叉的股票, 默认取当天的数据
     * @throws InterruptedException
     */
    GoodStocks getGoodStocks(String traceId, String queryDate);
}

