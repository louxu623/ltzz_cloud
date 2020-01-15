package com.ltzz.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.LtFollowTweet;
import com.ltzz.modules.base.entity.Tweet7dTop50;

public interface LtFollowTweetService extends IService<LtFollowTweet> {
    void queryOrUpdateData(String traceId);

    LtFollowTweet getDataBySymbol(String traceId, String symbol);
}

