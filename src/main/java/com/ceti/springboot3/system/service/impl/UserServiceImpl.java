package com.ceti.springboot3.system.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceti.springboot3.common.constant.SystemConstants;
import com.ceti.springboot3.system.converter.UserConverter;
import com.ceti.springboot3.system.mapper.UserMapper;
import com.ceti.springboot3.system.model.bo.UserBO;
import com.ceti.springboot3.system.model.entity.User;
import com.ceti.springboot3.system.model.form.UserForm;
import com.ceti.springboot3.system.model.query.UserPageQuery;
import com.ceti.springboot3.system.model.vo.UserPageVO;
import com.ceti.springboot3.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 用户业务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserConverter userConverter;

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public String login(String username, String password) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .eq(User::getPassword, SaSecureUtil.md5(password))
        );
        if (user != null) {
            StpUtil.login(user.getId());
            return StpUtil.getTokenValue();
        }
        return null;
    }

    @Override
    public IPage<UserPageVO> getUserPage(UserPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);
        Page<UserBO> userPage = this.baseMapper.getUserPage(page, queryParams);
        return userConverter.toPageVo(userPage);
    }

    @Override
    public UserForm getUserFormData(Long userId) {
        return this.baseMapper.getUserFormData(userId);
    }

    @Override
    public boolean saveUser(UserForm userForm) {
        User entity = userConverter.toEntity(userForm);
        // 设置默认密码
        String pwd = SaSecureUtil.md5(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(pwd);
        return this.save(entity);
    }

    @Override
    public boolean updateUser(Long userId, UserForm userForm) {
        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .ne(User::getId, userId)
        );
        Assert.isTrue(count == 0, "用户名已存在");
        // form -> entity
        User entity = userConverter.toEntity(userForm);

        // 修改用户
        boolean result = this.updateById(entity);
        // TODO 保存用户角色
        return result;
    }

    @Override
    public boolean deleteUsers(String idsStr) {
        return false;
    }
}
