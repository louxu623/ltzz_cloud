/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
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
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        // secureRegistry.excludePathPatterns("/token/**").excludePathPatterns("/customer/**");
        secureRegistry.excludePathPatterns("/token/**").excludePathPatterns("/outside/**")
                .excludePathPatterns("/common/**").excludePathPatterns("/appupdateversion/**")
                .excludePathPatterns("/ezt/eztreport/loginSend").excludePathPatterns("/ezt/eztreport/captcha.jpg")
                .excludePathPatterns("/ezt/eztreport/send/**").excludePathPatterns("/ezt/eztreport/get/**")
                .excludePathPatterns("/ystp/topInfo").excludePathPatterns("/ystp/stock")
        .excludePathPatterns("/outside/rechargeable/findCardByCardNo")
        .excludePathPatterns("/outside/rechargeable/getCompanyEntityById")
        .excludePathPatterns("/outside/transfer/getRecordsBySeqId")
        .excludePathPatterns("/outside/bd/getById")
        .excludePathPatterns("/outside/rechargeable/selectMgrEntityByManagerId")
        ;
        return secureRegistry;

    }

    // public static void main(String[] args) {
    // Map<String, Object> map = new HashMap<>();
    // map.put("id", 0);
    // map.put("name", "全部");
    // map.put("id", 1);
    // map.put("name", "女装");
    // System.out.println(JsonUtil.toJson(Arrays.asList(map)));
    // }

}
