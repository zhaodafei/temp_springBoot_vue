package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.core.controller.BaseController;
import com.example.fei.common.core.page.TableDataInfo;
import com.example.fei.domain.DictData;
import com.example.fei.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictData")
public class DictDataController extends BaseController {
    @Autowired
    private DictDataService dictDataService;

    @GetMapping("/list")
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> dictTypeList = dictDataService.selectDictTypeList(dictData);
        return getDataTable(dictTypeList);
    }

    @GetMapping("/query")
    public TableDataInfo query(String dictType) { // todo: 待调整
        List<DictData> dictTypeList = dictDataService.queryDictDataByType(dictType);
        return getDataTable(dictTypeList);
    }

    @GetMapping("/detail")
    public AjaxResult detail(String dictCode) {
        DictData details = dictDataService.selectDictDataById(dictCode);

        AjaxResult ajax = AjaxResult.success("success");
        ajax.put(AjaxResult.DATA_TAG, details);
        // if (true) {}
        return ajax;
    }

    /**
     * {
     *     "dictSort": 1,
     *     "dictLabel":"开",
     *     "dictValue":"0",
     *     "dictType":"switch_state",
     *     "status":0,
     *     "remark":"无"
     * }
     * @param dictData
     * @return
     */
    @PostMapping("add")
    public AjaxResult add(@RequestBody DictData dictData) {
        return toAjax(dictDataService.insertDictData(dictData));
    }

    @PostMapping("update")
    public AjaxResult update(@RequestBody DictData dictData) {
        return toAjax(dictDataService.updateDictData(dictData));
    }

    @GetMapping("del")
    public AjaxResult del(String dictCodes) {
        return toAjax(dictDataService.delDictType(dictCodes));
    }

}
