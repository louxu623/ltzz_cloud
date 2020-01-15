package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 调拨记录表
 *
 * @author luox
 * @date 2019/8/15
 */
@Data
@TableName("b_transfer_record")
public class TransferRecordEntity implements Serializable {
    private static final long serialVersionUID = 7173955617961842646L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 开始序列号
     */
    private String startSeq;
    /**
     * 结束序列号
     */
    private String endSeq;
    /**
     * BDID
     */
    private Long bdId;
    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 创建人Id
     */
    @JsonIgnore
    private Long createId;
    /**
     * 修改人Id
     */
    @JsonIgnore
    private Long updateId;
    /**
     * 备注
     */
    @JsonIgnore
    private String remarks;
    /**
     * 版本
     */
    @JsonIgnore
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */
    @JsonIgnore
    private Integer enable;
}
