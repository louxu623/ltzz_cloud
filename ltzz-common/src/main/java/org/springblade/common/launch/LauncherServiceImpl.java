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
package org.springblade.common.launch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

/**
 * 启动参数拓展
 *
 * @author smallchil
 */
public class LauncherServiceImpl implements LauncherService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        props.setProperty("spring.cloud.nacos.discovery.server-addr", profile.equals(AppConstant.DEV_CDOE) ? CommonConstant.NACOS_DEV_ADDR : profile.equals(AppConstant.TEST_CODE) ? CommonConstant.NACOS_TEST_ADDR : CommonConstant.NACOS_PROD_ADDR);
        props.setProperty("spring.cloud.nacos.config.server-addr", profile.equals(AppConstant.DEV_CDOE) ? CommonConstant.NACOS_DEV_ADDR : profile.equals(AppConstant.TEST_CODE) ? CommonConstant.NACOS_TEST_ADDR : CommonConstant.NACOS_PROD_ADDR);
        props.setProperty("spring.cloud.sentinel.transport.dashboard", profile.equals(AppConstant.DEV_CDOE) ? CommonConstant.SENTINEL_DEV_ADDR : profile.equals(AppConstant.TEST_CODE) ? CommonConstant.SENTINEL_TEST_ADDR : CommonConstant.SENTINEL_PROD_ADDR);
        logger.info("系统启动中,读取到的配置为:" + profile + "----spring.cloud.nacos.discovery.server-addr:" + props.get("spring.cloud.nacos.discovery.server-addr"));
        logger.info("系统启动中,读取到的配置为:" + profile + "----spring.cloud.nacos.config.server-addr:" + props.get("spring.cloud.nacos.config.server-addr"));
        logger.info("系统启动中,读取到的配置为:" + profile + "----spring.cloud.sentinel.transport.dashboard:" + props.get("spring.cloud.sentinel.transport.dashboard"));
    }

}
