package com.ceti.springboot3.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.system.model.bo.UserBO;
import com.ceti.springboot3.system.model.entity.User;
import com.ceti.springboot3.system.model.form.UserForm;
import com.ceti.springboot3.system.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


/**
 * 用户对象转换器
 *
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm toForm(User entity);

    @InheritInverseConfiguration(name = "toForm")
    User toEntity(UserForm entity);
}
