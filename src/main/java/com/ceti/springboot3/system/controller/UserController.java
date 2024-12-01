package com.ceti.springboot3.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceti.springboot3.common.result.PageResult;
import com.ceti.springboot3.common.result.Result;
import com.ceti.springboot3.system.model.form.UserForm;
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
@Tag(name = "02.用户接口")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户分页列表")
    @GetMapping("/page")
//    @Log(value = "用户分页列表", module = LogModuleEnum.USER)
    public PageResult<UserPageVO> getUserPage(
            @Valid UserPageQuery queryParams
    ) {
        IPage<UserPageVO> result = userService.getUserPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增用户")
    @PostMapping("add")
//    @PreAuthorize("@ss.hasPerm('sys:user:add')")
//    @RepeatSubmit
    public Result<?> saveUser(
            @RequestBody @Valid UserForm userForm
    ) {
        boolean result = userService.saveUser(userForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/{userId}")
    public Result<UserForm> getUserFormData(
            @PathVariable Long userId
    ) {
        UserForm result = userService.getUserFormData(userId);
        return Result.success(result);
    }
}
