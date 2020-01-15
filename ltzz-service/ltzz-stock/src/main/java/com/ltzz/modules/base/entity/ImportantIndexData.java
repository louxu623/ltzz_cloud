package com.ltzz.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("nd_important_index_data")
public class ImportantIndexData implements Serializable {
    @TableId
    private Long id;
    private String stockCode;

    private Long bizDate;

    private Double volume;

    private Double amount;

    private Double close;

    private Double high;

    private Double low;

    private Double preClose;

    private Double open;

    private Double ma5;

    private Double ma10;

    private Double ma20;

    private Double diff;

    private Double dea;

    private Double macd;

    private Double k;

    private Double d;

    private Double j;

    private Double mainInFlow;

    private Double retailInFlow;

    private Double mainOutFlow;

    private Double retailOutFlow;

    private Long createTime;

    private Long updateTime;
}
