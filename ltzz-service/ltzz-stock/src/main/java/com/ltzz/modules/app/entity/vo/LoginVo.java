package com.ltzz.modules.app.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户ID")
    private Long customerId;
    @ApiModelProperty("公司id")
    private Long companyId;
    @ApiModelProperty("公司名称")
    private String companyName;
    @ApiModelProperty("令牌")
    private String accessToken;
    @ApiModelProperty("令牌类型")
    private String tokenType;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("过期时间")
    private long expiresIn;
    @ApiModelProperty("许可证")
    private String license = "powered by sqqmall";
}
