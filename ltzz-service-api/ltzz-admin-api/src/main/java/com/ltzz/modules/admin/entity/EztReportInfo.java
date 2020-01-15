package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjc
 * @date 2019-06-11 14:16:50
 */
@Data

public class EztReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 报告url
     */
    private String reportUrl;
    /**
     *
     */
    private String customerMobile;
    /**
     *
     */
    private String cardNo;
    /**
     *
     */
    private String reportId;
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
     * 有效性
     */
    private Integer enable;
    /**
     * 版本
     */
    private Long version;

}
