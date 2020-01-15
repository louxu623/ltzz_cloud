package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lt_follow_tweet")
public class LtFollowTweet {
    @TableId
    private Long id;

    private String symbol;

    private String code;

    private String name;

    private Double currentPrice;

    private Integer follow;

    private Integer followNew;

    private Integer followRank;

    private Integer followNewRank = 0;

    private Integer followRankChange;

    private Integer tweet;

    private Integer tweetNew;

    private Integer tweetRank;

    private Integer tweetNewRank = 0;

    private Integer tweetRankChange;

    private String triggerDate;

    private String remark;

    private Long createTime;

    private Long updateTime;
}