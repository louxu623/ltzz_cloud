package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author luox
 * @description 一周新增讨论数排行TOP50
 * @date 2019/10/29
 */
@Data
@TableName("lt_tweet7d_top50")
public class Tweet7dTop50 {
    @TableId
    private Long id;
    /**
     * 代码
     */
    private String symbol;
    /**
     * 简码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 当前价格（收盘价）：29.37
     */
    @JsonProperty("current")
    private Double currentPrice;
    /**
     * 7天新增讨论数
     */
    private Integer tweet7d;
    /**
     * 入选日期：20191020
     */
    private String triggerDate;
    /**
     * 描述
     */
    private String remark;
    /**
     * 创建时间（13位时间戳）
     */
    private Long createTime;
    /**
     * 修改时间（13位时间戳）
     */
    private Long updateTime;
}
