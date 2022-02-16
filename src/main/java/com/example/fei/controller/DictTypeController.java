package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.core.controller.BaseController;
import com.example.fei.common.core.page.TableDataInfo;
import com.example.fei.domain.DictType;
import com.example.fei.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController {
    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping("/list")
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> dictTypeList = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(dictTypeList);
    }

    @GetMapping("/detail")
    public AjaxResult detail(Long dictId) {
        DictType detail = dictTypeService.selectDictTypeDetail(dictId);

        AjaxResult ajax = AjaxResult.success("success");
        ajax.put(AjaxResult.DATA_TAG, detail);
        // if (true) {}
        return ajax;
    }

    /**
     * {
     *     "dictName": "分类开关01",
     *     "dictType":"switch_state-3",
     *     "status":0,
     *     "remark":"分类开关列表"
     * }
     * @param
     * @return
     */
    @PostMapping("add")
    public AjaxResult add(@RequestBody DictType dictType) {
        // todo: 需要校验唯一
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    @PostMapping("update")
    public AjaxResult update(@RequestBody DictType dictType) {
        return toAjax(dictTypeService.updateDictType(dictType));
    }

    @GetMapping("del")
    public AjaxResult del(String dictIds) {
        return toAjax(dictTypeService.delDictType(dictIds));
    }

}
