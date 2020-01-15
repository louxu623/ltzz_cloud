/**
 * Copyright (c) 2019-2028, wenjunchi Zhuang 庄骞 (smallwenjunchi@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sqqmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Desk启动器
 *
 * @author WenJunChi
 */
@SpringCloudApplication
@EnableFeignClients(value = {AppConstant.BASE_PACKAGES, AppConstant.BASE_COUPON_PACKAGES})
//@ComponentScan(basePackages = {"com.sqqmall.**"})
@SpringBootApplication
@MapperScan(basePackages = "classpath*:/mapper/**/*.xml")
public class CustomerApplication {
    public static void main(String[] args) {
        BladeApplication.run(AppConstant.APPLICATION_CUSTOMER_NAME, CustomerApplication.class, args);
    }

}

