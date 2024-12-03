package com.ceti.springboot3.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.common.model.Option;
import com.ceti.springboot3.system.model.entity.DictData;
import com.ceti.springboot3.system.model.form.DictDataForm;
import com.ceti.springboot3.system.model.vo.DictPageVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 字典项 对象转换器
 *
 */
@Mapper(componentModel = "spring")
public interface DictDataConverter {

    Page<DictPageVO> toPageVo(Page<DictData> page);

    DictDataForm toForm(DictData entity);

    DictData toEntity(DictDataForm formFata);

    Option<Long> toOption(DictData dictData);
    List<Option<Long>> toOption(List<DictData> dictData);
}
