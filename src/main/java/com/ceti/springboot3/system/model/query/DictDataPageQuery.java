package com.ceti.springboot3.system.model.query;


import com.ceti.springboot3.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description ="字典数据分页查询对象")
public class DictDataPageQuery extends BasePageQuery {

    @Schema(description="关键字(字典数据标签/值)")
    private String keywords;

    @Schema(description="字典编码")
    private String dictCode;

}
