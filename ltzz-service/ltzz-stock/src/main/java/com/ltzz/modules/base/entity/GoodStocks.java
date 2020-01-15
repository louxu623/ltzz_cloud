package com.ltzz.modules.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author luox
 * @description
 * @date 2020/1/3
 */
@Data
public class GoodStocks implements Serializable {
    private static final long serialVersionUID = -207642176939506727L;
    List<StockAnalysis> macdGlodStocks;
    /**
     * MACD金叉 + KDJ金叉
     */
    List<StockAnalysis> superGoodStocks;
    /**
     * MACD金叉 + KDJ金叉 + MA金叉
     */
    List<StockAnalysis> superSupperGoodStocks;
}
