package com.ltzz.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.customer.entity.CustomerEntity;
import org.springblade.common.tool.PageUtils;

import java.util.Map;

/**
 * 会员基础信息表
 *
 * @author wenjc
 * @email wenjc@ltzz.com
 * @date 2019-03-30 10:26:11
 */
public interface CustomerService extends IService<CustomerEntity> {
    PageUtils queryPage(Map<String, Object> params);

}

