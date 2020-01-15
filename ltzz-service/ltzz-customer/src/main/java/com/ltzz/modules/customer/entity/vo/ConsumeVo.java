package com.sqqmall.modules.customer.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConsumeVo implements Serializable {
    private static final long serialVersionUID = -8316187353771914416L;

    /**
     * 抵扣平台<参数管理>
     */
    private String deduction;
    /**
     * 平台名
     */
    private String seller;
    /**
     * 抵扣券金额
     */
    private BigDecimal coupon_amount;
    /**
     * 券后价
     */
    private BigDecimal zf_price;
    /**
     * 原价
     */
    private BigDecimal zk_final_price;
    /**
     * 是否天猫
     */
    private Boolean tm = false;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 商品图片
     */
    private String pict_url;
    /**
     * 商品Id
     */
    private String skuId;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 链接
     */
    private String url;
    /**
     * 商品类别
     */
    private String cid = "0";
    /**
     * 剩余券数
     */
    private int surplus;
}
