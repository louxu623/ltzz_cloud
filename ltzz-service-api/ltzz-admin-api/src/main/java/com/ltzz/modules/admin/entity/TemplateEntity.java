package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 模板表
 * 
 * @author wjc
 * @date 2019-06-17 11:38:33
 */
@Data
@TableName("b_template")
public class TemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 单卡绑定返额
	 */
	private BigDecimal bindAmount;
	/**
	 * 单卡激活返额
	 */
	private BigDecimal activityAmount;
	/**
	 * 比例1
	 */
	private Float proportionOne;
	/**
	 * 比例2
	 */
	private Float proportionTwo;
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
