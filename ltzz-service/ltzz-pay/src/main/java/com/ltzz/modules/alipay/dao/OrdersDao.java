package com.ltzz.modules.alipay.dao;

import com.ltzz.modules.alipay.entity.Orders;
import com.ltzz.modules.alipay.entity.OrdersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersDao {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}