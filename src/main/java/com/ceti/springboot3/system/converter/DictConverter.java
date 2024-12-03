package com.ceti.springboot3.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.system.model.entity.Dict;
import com.ceti.springboot3.system.model.form.DictForm;
import com.ceti.springboot3.system.model.vo.DictPageVO;
import org.mapstruct.Mapper;

/**
 * 字典 对象转换器
 *
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> toPageVo(Page<Dict> page);

    DictForm toForm(Dict entity);

    Dict toEntity(DictForm entity);
}
