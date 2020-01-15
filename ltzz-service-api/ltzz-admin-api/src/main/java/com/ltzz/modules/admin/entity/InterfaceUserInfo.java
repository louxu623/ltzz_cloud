package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjc
 * @date 2019-06-14 10:41:52
 */
@Data

public class InterfaceUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     *
     */
    private Long companyId;
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
    private Long enable;

}
