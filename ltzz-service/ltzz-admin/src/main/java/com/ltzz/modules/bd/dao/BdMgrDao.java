package com.ltzz.modules.bd.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltzz.modules.admin.entity.BdMgrEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * BD管理DAO
 * @author luox
 * @date 2019/8/16
 */
@Mapper
public interface BdMgrDao extends BaseMapper<BdMgrEntity> {
}
