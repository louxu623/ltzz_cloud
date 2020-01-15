package com.sqqmall.modules.bd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.admin.entity.BdMgrEntity;

/**
 * BD管理Service
 * @author luox
 * @date 2019/8/16
 */
public interface BdMgrService extends IService<BdMgrEntity> {
    /**
     * 根据BD ID 获取BD信息
     * @author luox
     * @date 2019/8/16
     * @param bdId    bd id
     * @return        BD基础信息
     */
    BdMgrEntity getBdInfoByBdId(Long bdId);
}

