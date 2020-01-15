package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;

public class StockAnalysis {
    @TableId
    private Long id;
    private Integer bizDate;

    private String stockCode;

    private Byte analysisType;

    private Double close;

    private Double preClose;

    private Double open;

    private Byte isUp;

    private Byte isUpInterrupt;

    private Integer upDays;

    private Integer createTime;

    private Integer updateTime;

    public StockAnalysis(Integer bizDate, String stockCode, Byte analysisType, Double close, Double preClose, Double open, Byte isUp,
                         Byte isUpInterrupt, Integer upDays, Integer createTime, Integer updateTime) {
        super();
        this.bizDate = bizDate;
        this.stockCode = stockCode;
        this.analysisType = analysisType;
        this.close = close;
        this.preClose = preClose;
        this.open = open;
        this.isUp = isUp;
        this.isUpInterrupt = isUpInterrupt;
        this.upDays = upDays;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
