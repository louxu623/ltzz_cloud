package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 砸金蛋中奖记录表
 * @author luox
 * @date 2019/8/5
 */
@Data
@TableName("b_egg_winning_record")
public class EggWinningRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long customerId;
    /**
     * 用户手机号/账号
     */
    private String mobile;
    /**
     * 奖品类型(1：购物余额 2：实物商品 3:虚拟卡券)
     */
    private Integer prizeType;
    /**
     * 中奖金额，奖品为购物余额时必须有
     */
    private BigDecimal prizeAmount;
    /**
     * 商品ID（当奖品为实物时才会有）
     */
    private Long prizeId;
    /**
     * 奖品别名
     */
    private String prizeAlias;
    /**
     * 会员卡卡号
     */
    private String cardNum;
    /**
     * 会员卡密码或兑换券兑换码
     */
    private String password;
    /**
     * 有效时间
     */
    private Date indate;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人Id
     */
    private Long createId;
    /**
     * 修改人Id
     */
    private Long updateId;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 版本
     */
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */
    private Integer enable;

}
