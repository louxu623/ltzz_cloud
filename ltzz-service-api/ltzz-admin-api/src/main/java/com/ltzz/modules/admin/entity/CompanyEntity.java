package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 企业表
 * @author lq
 * @date 2019\5\23 0023 10:09
 */

@Data
@TableName("b_company")
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公司姓名
     */
    private String name;
    /**
     * 管理id
     */
    //private Long mgrId;
    /**
     * 电话号码
     */
    private String phone;

    /**
     * 公司负责人2
     */
    private String phoneTwo;
    /**
     * 父公司id
     */
    private Long parentId;
    /**  级别  0：普通   1：合伙人  */
    private Integer level;
    /**
     * 号段
     */
    private String number;

    /**
     * 邀请码
     */
    private String code;
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
    /**
     * 子公司集合
     */
    @TableField(select = false)
    private List<CompanyEntity> list;

}
