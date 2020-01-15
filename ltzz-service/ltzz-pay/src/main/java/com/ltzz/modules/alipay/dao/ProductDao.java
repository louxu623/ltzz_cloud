package com.ltzz.modules.alipay.dao;

import com.ltzz.modules.alipay.entity.Product;
import com.ltzz.modules.alipay.entity.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}