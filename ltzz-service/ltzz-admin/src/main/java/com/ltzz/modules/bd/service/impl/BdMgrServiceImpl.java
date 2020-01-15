package com.ltzz.modules.bd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.admin.entity.BdMgrEntity;
import com.ltzz.modules.bd.dao.BdMgrDao;
import com.sqqmall.modules.bd.service.BdMgrService;
import org.springframework.stereotype.Service;

/**
 * 卡调拨记录service实现类
 * @author luox
 * @date 2019/8/15
 */
@Service("bdMgrService")
public class BdMgrServiceImpl extends ServiceImpl<BdMgrDao, BdMgrEntity> implements BdMgrService {
    @Override
    public BdMgrEntity getBdInfoByBdId(Long bdId) {
        return this.getOne(new QueryWrapper<BdMgrEntity>().eq("id", bdId));
    }
}