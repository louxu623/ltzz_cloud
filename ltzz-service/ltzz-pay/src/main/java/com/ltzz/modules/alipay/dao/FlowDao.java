package com.ltzz.modules.alipay.dao;

import com.ltzz.modules.alipay.entity.Flow;
import com.ltzz.modules.alipay.entity.FlowExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowDao {
    int countByExample(FlowExample example);

    int deleteByExample(FlowExample example);

    int deleteByPrimaryKey(String id);

    int insert(Flow record);

    int insertSelective(Flow record);

    List<Flow> selectByExample(FlowExample example);

    Flow selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Flow record, @Param("example") FlowExample example);

    int updateByExample(@Param("record") Flow record, @Param("example") FlowExample example);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}