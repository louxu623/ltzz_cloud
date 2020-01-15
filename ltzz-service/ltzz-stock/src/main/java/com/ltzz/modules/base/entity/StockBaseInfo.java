package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author luox
 * @description 基础信息
 * @date 2019/10/28
 */
@Data
@TableName("lt_stock_base_info")
public class StockBaseInfo {
    @TableId
    private Long id;
    /**
     * 代码
     */
    private String symbol;
    /**
     * 名称
     */
    private String name;
    /**
     * 最新价
     */
    private Double trade;
    /**
     * 涨跌额
     */
    private Double pricechange;
    /**
     * 涨跌幅
     */
    private Double changepercent;
    /**
     * 买入
     */
    private Double buy;
    /**
     * 卖出
     */
    private Double sell;
    /**
     * 昨收
     */
    private Double settlement;
    /**
     * 今开
     */
    private Double open;
    /**
     * 最高
     */
    private Double high;
    /**
     * 最低
     */
    private Double low;
    /**
     * 成交量
     */
    private Long volume;
    /**
     * 成交额
     */
    private Long amount;
    /**
     * 简码
     */
    private String code;
    /**
     * 时间
     */
    private String ticktime;
    /**
     * 创建时间（13位时间戳）
     */
    private Long createTime;
    /**
     * 修改时间（13位时间戳）
     */
    private Long updateTime;
}
