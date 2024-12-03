package com.ceti.springboot3.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 用户登录表单对象
 */
@Schema(description = "用户登录表单对象")
@Data
public class UserLoginForm {

    @Schema(description="用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description="密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
