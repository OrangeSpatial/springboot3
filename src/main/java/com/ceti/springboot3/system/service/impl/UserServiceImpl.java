package com.ceti.springboot3.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 *
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
        return this.save(userConverter.toEntity(userForm));
    }

    @Override
    public boolean updateUser(Long userId, UserForm userForm) {
        return false;
    }

    @Override
    public boolean deleteUsers(String idsStr) {
        return false;
    }
}
