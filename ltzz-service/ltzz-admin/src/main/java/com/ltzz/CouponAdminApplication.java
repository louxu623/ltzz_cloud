/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.sqqmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringCloudApplication
@EnableCaching
@EnableAsync
@EnableFeignClients(value = {AppConstant.BASE_PACKAGES, AppConstant.BASE_COUPON_PACKAGES})
//@ComponentScan(basePackages = {"com.sqqmall.**"})

@MapperScan(basePackages = "classpath*:/mapper/**/*.xml")
@SpringBootApplication
public class CouponAdminApplication {
    public static void main(String[] args) {
        BladeApplication.run(AppConstant.APPLICATION_ADMIN_NAME, CouponAdminApplication.class, args);

    }

}