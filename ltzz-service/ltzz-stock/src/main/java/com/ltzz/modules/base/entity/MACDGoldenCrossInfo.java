package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author luox
 * @description MACD金叉股票信息表
 * @date 2019/10/29
 */
@Data
@TableName("lt_macd_golden_cross_info")
public class MACDGoldenCrossInfo {
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
     * 入选价格（收盘价）：29.37
     */
    private Double triggerPrice;
    /**
     * 入选时间：1572247862690
     */
    private Long triggerTime;
    /**
     * 入选日期：20191020
     */
    private String triggerDate;
    /**
     * 描述
     */
    @JsonProperty("desc")
    private String description;
    /**
     * 创建时间（13位时间戳）
     */
    private Long createTime;
    /**
     * 修改时间（13位时间戳）
     */
    private Long updateTime;

    @TableField(exist = false)
    private boolean isNew;
}
