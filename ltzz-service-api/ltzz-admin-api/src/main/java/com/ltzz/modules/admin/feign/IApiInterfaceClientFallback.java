package com.ltzz.modules.admin.feign;

import com.ltzz.modules.admin.entity.EggWinningRecordEntity;
import com.ltzz.modules.admin.entity.InterfaceUserInfo;
import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author WenJunChi
 * @date 2019\5\6 0006
 */

@Component
public class IApiInterfaceClientFallback implements IApiInterfaceClient {


    @Override
    public R<InterfaceUserInfo> getUser(String username, String password) {
        return R.fail("获取接口登陆用户信息失败");
    }

    @Override
    public R saveEggRecord(EggWinningRecordEntity entity) {
        return R.fail("保存砸金蛋中奖记录信息失败");
    }

    @Override
    public R<List<EggWinningRecordEntity>> getWinningList(Long customerId) {
        return R.fail("获取砸金蛋中奖记录信息失败");
    }

    @Override
    public R getCustomerWinningList(Long customerId, String page, String limit) {
        return R.fail("获取用户中奖信息失败");
    }
}
