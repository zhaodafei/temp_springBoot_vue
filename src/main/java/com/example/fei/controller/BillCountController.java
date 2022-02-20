package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.core.controller.BaseController;
import com.example.fei.common.core.page.TableDataInfo;
import com.example.fei.domain.BillCount;
import com.example.fei.domain.Goods;
import com.example.fei.service.BillCountService;
import com.example.fei.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/billCount")
public class BillCountController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BillCountService billCountService;

    /**
     * 分页查询
     * @param billCount {}
     * @return {}
     */
    @GetMapping("list")
    public TableDataInfo list(BillCount billCount) {
        startPage();
        List<BillCount> billCounts = billCountService.selectBillList(billCount);
        return getDataTable(billCounts);
    }

    @GetMapping("budget")
    public AjaxResult Budget(Goods goods) {
        Goods goodsBudget = goodsService.selectBudget(goods);

        AjaxResult ajax = AjaxResult.success("success");
        ajax.put("allCount", goodsBudget != null ? goodsBudget.getAllCount() : 0);
        ajax.put("consumeWay", "字典参数开发中"); // todo: 字典参数开发中
        return ajax;
    }

    @PostMapping("add")
    public AjaxResult add(@RequestBody Goods goods) {
        // todo: 字典参数开发中
        Goods goodsBudget = goodsService.selectBudget(goods);
        BillCount billCount = new BillCount();

        billCount.setStartTime(goods.getStartTime());
        billCount.setEndTime(goods.getEndTime());
        billCount.setConsumeWay("所有");  // todo: 字典参数开发中
        billCount.setAllCount(goodsBudget != null ? goodsBudget.getAllCount() : BigDecimal.valueOf(0));

        return toAjax(billCountService.insertBill(billCount));
    }

    @GetMapping("del")
    public AjaxResult del(String ids) {
        return toAjax(billCountService.delBill(ids));
    }

}
