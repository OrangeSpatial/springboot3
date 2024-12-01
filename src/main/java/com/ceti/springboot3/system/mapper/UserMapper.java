package com.ceti.springboot3.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.system.model.form.UserForm;
import com.ceti.springboot3.system.model.bo.UserBO;
import com.ceti.springboot3.system.model.entity.User;
import com.ceti.springboot3.system.model.query.UserPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户持久层
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<UserBO> getUserPage(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserForm getUserFormData(Long userId);
}
