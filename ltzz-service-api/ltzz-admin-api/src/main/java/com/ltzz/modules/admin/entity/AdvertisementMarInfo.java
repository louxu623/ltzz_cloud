package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdvertisementMarInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */

    private Long id;
    /**
     * 广告位
     */
    private String advertising;
    /**
     * 类型  0 ：用户主页 1 ：更多省钱
     */
    private Integer type;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 图片地址
     */
    private String image;
    /**
     * 链接地址
     */
    private String linkaddress;
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
