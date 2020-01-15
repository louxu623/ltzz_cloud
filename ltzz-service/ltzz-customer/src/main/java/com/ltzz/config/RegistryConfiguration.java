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
package com.sqqmall.config;


import org.springblade.core.secure.registry.SecureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * secure模块api放行配置
 *
 * @author WJunChi
 */
@Configuration
public class RegistryConfiguration implements WebMvcConfigurer {

    @Bean
    public SecureRegistry secureRegistry() {
        SecureRegistry secureRegistry = new SecureRegistry();
        secureRegistry.excludePathPatterns("/token/**").excludePathPatterns("/app/**")
                .excludePathPatterns("/outsite/**").excludePathPatterns("/sys/deviceModel")
                .excludePathPatterns("/customer/sku-info")
                .excludePathPatterns("/customer/h5red/**").excludePathPatterns("/customer/rebate")
                .excludePathPatterns("/customer/user_id")
                .excludePathPatterns("/advswith/get")
                .excludePathPatterns("/recharge/customer/getUserCustomerIdAndTimeInfo")
                .excludePathPatterns("/recharge/customer/isExistRechargeByCustomerId")
                .excludePathPatterns("/customer/h5red/**")
                .excludePathPatterns("/customer/send-code/**")
                .excludePathPatterns("/customer/first-rechange")
                .excludePathPatterns("/customer/bind-record")
                .excludePathPatterns("/customer/infoById")
                .excludePathPatterns("/customer/customer_ids")
                .excludePathPatterns("/h5/**")
                .excludePathPatterns("/customer/egg/getLotteryNumber")
                .excludePathPatterns("/rechargeable/consume/allConsumeList/**")//大券秒杀接口不走授权
                .excludePathPatterns("/celebrity/members/**")
                .excludePathPatterns("/coupon-customer/wealth/**")
                .excludePathPatterns("/doubleelevenactivityregister/invitation/invitationregister")
                .excludePathPatterns("/customer/customer_ids");

        return secureRegistry;

    }


}
