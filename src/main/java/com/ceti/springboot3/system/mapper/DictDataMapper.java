package com.ceti.springboot3.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.system.model.entity.DictData;
import com.ceti.springboot3.system.model.query.DictDataPageQuery;
import com.ceti.springboot3.system.model.vo.DictDataPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据映射层
 *
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 字典数据分页列表
     */
    Page<DictDataPageVO> getDictDataPage(Page<DictDataPageVO> page, DictDataPageQuery queryParams);
}




