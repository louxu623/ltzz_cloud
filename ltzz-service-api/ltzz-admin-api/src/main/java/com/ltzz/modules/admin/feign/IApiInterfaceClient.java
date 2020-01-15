package com.ltzz.modules.admin.feign;

import com.ltzz.modules.admin.entity.EggWinningRecordEntity;
import com.ltzz.modules.admin.entity.InterfaceUserInfo;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lq
 * @date 2019\5\6 0006
 */

@FeignClient(
        value = AppConstant.APPLICATION_ADMIN_NAME,
        fallback = IApiInterfaceClientFallback.class
)

public interface IApiInterfaceClient {

    final String API_PREFIX = "/outside/api";

    /**
     * 通过账号密码获取信息
     */
    @GetMapping(API_PREFIX + "/info-username-password")
    R<InterfaceUserInfo> getUser(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 保存砸金蛋活动中奖记录
     * @author luox
     * @date 2019/8/6
     * @param entity
     * @return
     */
    @PostMapping(API_PREFIX + "/saveEggRecord")
    R saveEggRecord(@RequestBody EggWinningRecordEntity entity);

    /**
     * 通过账号密码获取信息
     */
    @GetMapping(API_PREFIX + "/getWinningList")
    R<List<EggWinningRecordEntity>> getWinningList(@RequestParam("customerId") Long customerId);


    /**
     * 获取当前用户中奖记录
     */
    @GetMapping(API_PREFIX + "/getCustomerWinningList")
    R getCustomerWinningList(@RequestParam("customerId") Long customerId,@RequestParam("page") String page,@RequestParam("limit") String limit);

}
