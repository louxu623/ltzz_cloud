package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 公司发行权限关系表
 * 
 * @author wjc
 * @date 2019-06-17 11:38:33
 */
@Data
@TableName("b_company_authority_relationship")
public class CompanyAuthorityRelationshipEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 公司id
	 */
	private Long companyId;
	/**
	 * 一级发行权限 0 无 1 有
	 */
	private Integer authority;
	/**
	 * 总额度数上线
	 */
	private BigDecimal totalAmount;
	/**
	 * 模板id
	 */
	private Long templateId;
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
