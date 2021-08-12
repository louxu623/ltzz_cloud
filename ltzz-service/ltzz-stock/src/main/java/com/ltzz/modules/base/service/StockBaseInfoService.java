package com.ltzz.modules.base.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltzz.modules.base.entity.StockBaseInfo;
import com.ltzz.util.StockUtil;
import com.vpiaotong.openapi.util.HttpUtils;
import org.springblade.common.tool.PageUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StockBaseInfoService extends IService<StockBaseInfo> {
    void getBaseInfo(List<StockBaseInfo> stocks, int code, int page, int type, int typeStep);
    void updateStockSort(String traceId, StockBaseInfo stock);
}

