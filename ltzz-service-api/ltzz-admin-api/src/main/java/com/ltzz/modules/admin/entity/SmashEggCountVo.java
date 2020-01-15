package com.ltzz.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 砸蛋次数数据实体
 *
 * @author luox
 * @email luox@sqqmall.com
 * @date 2019/8/2
 */
@Data
public class SmashEggCountVo implements Serializable {
    private static final long serialVersionUID = -8830466936125362787L;
    /**
     * 当天可用次数
     */
    private int availableCount;
    /**
     * 每日赠送次数（每日固定两次）
     */
    private int loginGiveCount;
    /**
     * 下单赠送次数（每日最多三次，当该数小于3时说明还可以通过下单获得砸蛋次数）
     */
    private int giveCount;

    public SmashEggCountVo(int availableCount, int loginGiveCount, int giveCount) {
        this.availableCount = availableCount;
        this.loginGiveCount = loginGiveCount;
        this.giveCount = giveCount;
    }

    @Override
    public String toString() {
        return "SmashEggCountVo{" +
                "availableCount=" + availableCount +
                ", loginGiveCount=" + loginGiveCount +
                ", giveCount=" + giveCount +
                '}';
    }
}
