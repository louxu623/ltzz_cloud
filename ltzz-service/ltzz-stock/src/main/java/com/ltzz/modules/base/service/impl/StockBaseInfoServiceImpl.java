package com.ltzz.modules.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltzz.modules.base.dao.StockBaseInfoDao;
import com.ltzz.modules.base.entity.StockBaseInfo;
import com.ltzz.modules.base.service.StockBaseInfoService;
import com.ltzz.util.StockUtil;
import com.vpiaotong.openapi.util.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("stockBaseInfoService")
public class StockBaseInfoServiceImpl extends ServiceImpl<StockBaseInfoDao, StockBaseInfo> implements StockBaseInfoService {
    private static Log log = LogFactory.getLog(StockBaseInfoServiceImpl.class);

    @Override
    public void getBaseInfo(List<StockBaseInfo> stocks, int stype, int page, int type, int typeStep) {
        int totalCount = 0;
        String reqUrl = StockUtil.getBaseInfoUrl(stype, page, type);
        String reqResult = HttpUtils.get(reqUrl);
        JSONObject resObject = JSON.parseObject(reqResult);
        if ("0".equals(resObject.get("error_code").toString())) {
            JSONObject res = resObject.getJSONObject("result");
            totalCount = Integer.parseInt(res.get("totalCount").toString());
            JSONArray resutlDatas = res.getJSONArray("data");
            for (int i = 0; i < resutlDatas.size(); i++) {
                try {
                    StockBaseInfo stockObj = JSON.parseObject(resutlDatas.get(i).toString(), StockBaseInfo.class);
                    stockObj.setCreateTime(System.currentTimeMillis());
                    stocks.add(stockObj);
                } catch (Exception e) {
                    log.error("接口返回数据解析失败, url = " + reqUrl + ", res = " + resutlDatas.get(i).toString());
                }
            }
        } else {
            log.info("调用合众数据接口返回失败, url = " + reqUrl);
        }
        while (totalCount > page * type * typeStep) {  //  超过1页，则分页查询剩下的数据
            page++;
            reqUrl = StockUtil.getBaseInfoUrl(stype, page, type);
            getBaseInfo(stocks, stype, page, type, typeStep);
            return;
        }
    }
}
