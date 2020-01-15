/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.ltzz.modules.app.controller;

import com.ltzz.modules.admin.entity.InterfaceUserInfo;
import com.ltzz.modules.admin.feign.IApiInterfaceClient;
import com.ltzz.modules.app.entity.vo.LoginVo;
import com.ltzz.modules.app.form.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springblade.common.validator.ValidatorUtils;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.log.error.RRException;
import org.springblade.core.log.error.ServiceCode;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author Mark Wenjunchi
 */
@RestController
@RequestMapping("/app/login")
@Api(value = "远程登陆接口", tags = "远程登陆接口")
@AllArgsConstructor

public class AppLoginController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    IApiInterfaceClient interfaceClient;

    // final String rule = "ea57a4ccafb14be7";


    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("获取token")
    public R<LoginVo> login(@RequestBody LoginForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form);
        //用户登录
        String pwd = new Sha256Hash(form.getPassword()).toHex();
        form.setPassword(pwd);
//        CustomerEntity login = new CustomerEntity();
        R<InterfaceUserInfo> user = interfaceClient.getUser(form.getUsername(), form.getPassword());

        if (user.getCode() != 200) {
            throw new RRException("账号或者密码错误", ServiceCode.DB_SERVICE_ACCOUNT_ERROR.getCode());
        }
        InterfaceUserInfo data = user.getData();
        return R.data(createLoginInfo(data));
    }

    private LoginVo createLoginInfo(InterfaceUserInfo interfaceUserInfo) {
        //设置jwt参数
        Map<String, String> param = new HashMap<>(2);
        param.put(TokenConstant.USER_ID, Func.toStr(interfaceUserInfo.getId()));
        param.put(TokenConstant.USER_NAME, interfaceUserInfo.getUsername());
        //拼装accessToken
        String accessToken = SecureUtil.createJWT(param, "audience", "issuser", true);
        //返回accessToken
        LoginVo loginVo = new LoginVo();
        loginVo.setUserName(interfaceUserInfo.getUsername());
        loginVo.setAccessToken(accessToken);
        loginVo.setTokenType(TokenConstant.BEARER);
        loginVo.setMobile(interfaceUserInfo.getMobile());
        loginVo.setCustomerId(interfaceUserInfo.getId());
        loginVo.setCompanyId(interfaceUserInfo.getCompanyId());
        loginVo.setCompanyName(interfaceUserInfo.getCompanyName());
        //设置token过期时间
        loginVo.setExpiresIn(SecureUtil.getExpire());
        return loginVo;
    }
}
