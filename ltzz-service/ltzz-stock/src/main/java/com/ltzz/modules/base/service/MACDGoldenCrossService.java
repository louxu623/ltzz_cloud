package com.ltzz.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.MACDGoldenCrossInfo;
import com.ltzz.modules.base.entity.StockBaseInfo;

import java.util.List;

public interface MACDGoldenCrossService extends IService<MACDGoldenCrossInfo> {
    List<MACDGoldenCrossInfo> getAndUpdateTodayAllMACDGoldenCrossStock(String traceId, String queryDate, List<MACDGoldenCrossInfo> res);
}

