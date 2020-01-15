package com.ltzz.modules.admin.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CardInfo implements Serializable {

    private static final long serialVersionUID = -6580445829504319327L;
    /**
     * id
     */
    private Long cardId;
    /**
     * 抵扣金额
     */
    private BigDecimal faceValue;
    /**
     * 发卡单位名称
     */
    private String title;
    /**
     * 发卡单位Id
     */
    private Long companyId;
    /**
     * 卡生效时间
     */
    private Date startTime;
}
