package com.ltzz.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.customer.dao.CustomerDao;
import com.ltzz.modules.customer.entity.CustomerEntity;
import com.ltzz.modules.customer.service.CustomerService;
import org.springblade.common.tool.PageUtils;
import org.springblade.common.tool.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }
}
