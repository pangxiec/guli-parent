package com.example.ucenter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xmy
 * @data 2021/1/20 22:35
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}

