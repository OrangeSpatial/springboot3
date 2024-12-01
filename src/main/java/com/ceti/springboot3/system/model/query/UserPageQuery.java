package com.ceti.springboot3.system.model.query;

import cn.hutool.db.sql.Direction;
import com.ceti.springboot3.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户分页查询对象
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户分页查询对象")
public class UserPageQuery extends BasePageQuery {

    @Schema(description = "关键字(用户名/昵称/手机号)")
    private String keywords;

    @Schema(description = "用户状态")
    private Integer status;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "角色ID")
    private List<Long> roleIds;

    @Schema(description = "创建时间范围")
    private List<String> createTime;

    @Schema(description = "排序的字段")
//    @ValidField(allowedValues = {"create_time","update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;


}