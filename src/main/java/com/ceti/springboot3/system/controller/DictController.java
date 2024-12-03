package com.ceti.springboot3.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceti.springboot3.common.result.PageResult;
import com.ceti.springboot3.common.result.Result;
import com.ceti.springboot3.system.model.form.DictForm;
import com.ceti.springboot3.system.model.query.DictPageQuery;
import com.ceti.springboot3.system.model.vo.DictPageVO;
import com.ceti.springboot3.system.model.vo.DictVO;
import com.ceti.springboot3.system.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 字典控制层
 *
 */
@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;

    @Operation(summary = "字典分页列表")
    @GetMapping("/page")
    public PageResult<DictPageVO> getDictPage(
            DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典列表")
    @GetMapping("/list")
    public Result<List<DictVO>> getAllDictWithData() {
        List<DictVO> list = dictService.getAllDictWithData();
        return Result.success(list);
    }

    @Operation(summary = "字典表单")
    @GetMapping("/{id}/form")
    public Result<DictForm> getDictForm(
            @Parameter(description = "字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getDictForm(id);
        return Result.success(formData);
    }

    @Operation(summary = "新增字典")
    @PostMapping
    public Result<?> saveDict(@Valid @RequestBody DictForm formData) {
        boolean result = dictService.saveDict(formData);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典")
    @PutMapping("/{id}")
    public Result<?> updateDict(
            @PathVariable Long id,
            @RequestBody DictForm dictForm
    ) {
        boolean status = dictService.updateDict(id, dictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典")
    @DeleteMapping("/{ids}")
    public Result<?> deleteDictionaries(
            @Parameter(description = "字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        dictService.deleteDictByIds(Arrays.stream(ids.split(",")).toList());
        return Result.success();
    }

}
