package com.ceti.springboot3.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceti.springboot3.system.model.entity.User;
import com.ceti.springboot3.system.model.form.UserForm;
import com.ceti.springboot3.system.model.query.UserPageQuery;
import com.ceti.springboot3.system.model.vo.UserPageVO;

import java.util.List;

/**
 * 用户业务接口
 *
 */
public interface UserService extends IService<User> {

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    String login(String username, String password);

    /**
     * 用户分页列表
     *
     * @return
     */
    IPage<UserPageVO> getUserPage(UserPageQuery queryParams);


    /**
     * 获取用户表单数据
     *
     * @param userId
     * @return
     */
    UserForm getUserFormData(Long userId);


    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    boolean saveUser(UserForm userForm);

    /**
     * 修改用户
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return
     */
    boolean updateUser(Long userId, UserForm userForm);


    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String idsStr);
}
