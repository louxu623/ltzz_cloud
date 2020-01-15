package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BD管理实体对象
 *
 * @author luox
 * @date 2019/8/16
 */
@Data
@TableName("b_bd_mgr")
public class BdMgrEntity implements Serializable {
    private static final long serialVersionUID = 5587325027018933559L;
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * bd名称
     */
    private String name;
    /**
     * 隶属公司<参数管理>
     */
    private Long company;
    /**
     * 电话号码
     */
    private String phone;

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
