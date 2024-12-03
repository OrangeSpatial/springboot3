package com.ceti.springboot3.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceti.springboot3.common.result.PageResult;
import com.ceti.springboot3.common.result.Result;
import com.ceti.springboot3.common.result.ResultCode;
import com.ceti.springboot3.system.model.form.UserForm;
import com.ceti.springboot3.system.model.form.UserLoginForm;
import com.ceti.springboot3.system.model.query.UserPageQuery;
import com.ceti.springboot3.system.model.vo.UserPageVO;
import com.ceti.springboot3.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public Result<?> login(
            @RequestBody @Valid UserLoginForm userLoginForm
    ) {
        String result = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        if (result == null) {
            return Result.failed(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return Result.success(result);
    }

    @Operation(summary = "用户分页列表")
    @GetMapping("/page")
    @SaCheckLogin
//    @Log(value = "用户分页列表", module = LogModuleEnum.USER)
    public PageResult<UserPageVO> getUserPage(
            @Valid UserPageQuery queryParams
    ) {
        System.out.println(StpUtil.getTokenValue());
        IPage<UserPageVO> result = userService.getUserPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增用户")
    @PostMapping("add")
    @SaCheckLogin
    public Result<?> saveUser(
            @RequestBody @Valid UserForm userForm
    ) {
        boolean result = userService.saveUser(userForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/{userId}")
    @SaCheckLogin
    public Result<UserForm> getUserFormData(
            @PathVariable Long userId
    ) {
        UserForm result = userService.getUserFormData(userId);
        return Result.success(result);
    }

    @Operation(summary = "修改用户")
    @PutMapping("/{userId}")
    @SaCheckLogin
    public Result<?> updateUser(
            @PathVariable Long userId,
            @RequestBody @Valid UserForm userForm
    ) {
        boolean result = userService.updateUser(userId, userForm);
        return Result.judge(result);
    }
}
