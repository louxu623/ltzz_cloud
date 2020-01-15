package com.ltzz.modules.customer.controller;

import com.ltzz.modules.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 会员基础信息表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@RestController
@RequestMapping("customer")
@Api(value = "会员基础信息", description = "会员基础信息", tags = {"会员基础信息"})
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "参数", paramType = "query", dataType = "integer")
    })
    @ApiOperation(value = "分页", notes = "传入params", position = 1)

    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        return R.data(customerService.queryPage(params));
    }
}
