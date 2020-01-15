package com.ltzz.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.Tweet7dTop50;

import java.util.List;

public interface Tweet7dTopService extends IService<Tweet7dTop50> {
    List<Tweet7dTop50> getTweet7dTop50(String traceId, List<Tweet7dTop50> res);

    Tweet7dTop50 getTweet7dBySymbol(String traceId, String symbol);

    Tweet7dTop50 getTweetBySymbol(String traceId, String symbol);
}

