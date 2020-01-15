package com.ltzz.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.core.secure.BladeUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 砸金蛋活动中奖卡券表
 * 
 * @author lq
 * @date 2019-08-21 08:43:02
 */
@Data
public class CardVoucherRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 卡券类型(1：电影票抵扣券 ， 2：爱奇艺会员卡)
	 */
	private Integer type;
	/**
	 * 卡券名称
	 */
	private String title;
	/**
	 * 卡号
	 */
	private String cardNum;
	/**
	 * 会员卡密码或抵扣券兑换码
	 */
	private String password;
	/**
	 * 有效期
	 */
	private Date indate;
	/**
	 * 中奖人电话
	 */
	private String winnersMobile;
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

	private BladeUser user;

	private SmashEggCountVo smashEggCountVo;

	private String redisKey;

}
