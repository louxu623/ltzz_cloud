
package com.ltzz.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员基础信息表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Data
@TableName("b_customer")
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */

    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 性别 m:男;f:女;s:保密
     */

    private String sex;
    /**
     * 生日
     */

    private Date birthday;
    /**
     * 省
     */

    private String province;
    /**
     * 市编号
     */

    private String city;
    /**
     * 区
     */

    private String area;
    /**
     * 详细地址
     */

    private String address;
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

    @Version
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */

    private Integer enable;
//	/**
//	 * 用户第三方信息
//	 */
//	@TableField(exist = false)
////    
//		List<CustomerThirdpartyEntity> customerThirdpartyEntitys;
//	/**
//	 * 用户账户信息
//	 */
//	@TableField(exist = false)
//	
//	CustomerWealthEntity customerWealthEntity;

    @TableField(exist = false)
    private String openId;
    /**
     * 第三方参数ID
     */
    @TableField(exist = false)
    private Integer partyId;

    /**
     * 盐值
     */
    private String salt;
    /**
     *用户来源
     */
    private Integer source;

    @TableField(exist = false)
    private String relationId;


}
