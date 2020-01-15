package com.ltzz.modules.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltzz.modules.base.entity.ImportantIndexData;
import com.ltzz.modules.base.entity.Tweet7dTop50;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportantIndexDataDao extends BaseMapper<ImportantIndexData> {

}
