/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.ltzz.modules.app.controller;

import com.ltzz.modules.app.entity.vo.LoginVo;
import com.ltzz.modules.app.form.LoginForm;
import com.ltzz.modules.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * APP登录授权
 *
 * @author Mark Wenjunchi
 */
@RestController
@RequestMapping("/app/login")
@Api(value = "APP登录接口", tags = "用户登录接口")
public class AppLoginController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录-通过账号密码")
    @ApiLog
    public R<LoginVo> login(@RequestBody LoginForm form, HttpServletRequest request) {
        String header = request.getHeader("blade-auth");
        logger.info("登陆操作-获取用户token为：" + header);
        return R.data(null);
    }
}
