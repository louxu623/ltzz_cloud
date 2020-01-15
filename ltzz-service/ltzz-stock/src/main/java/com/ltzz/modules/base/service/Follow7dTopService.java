package com.ltzz.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.Follow7dTop50;
import com.ltzz.modules.base.entity.StockBaseInfo;

import java.util.List;

public interface Follow7dTopService extends IService<Follow7dTop50> {
    List<Follow7dTop50> getFollow7dTop50(String traceId, List<Follow7dTop50> res);

    Follow7dTop50 getFollow7dBySymbol(String traceId, String symbol);

    Follow7dTop50 getFollowBySymbol(String traceId, String symbol);
}

