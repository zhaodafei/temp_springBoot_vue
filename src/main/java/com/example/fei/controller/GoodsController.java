package com.example.fei.controller;

import com.example.fei.common.core.AjaxResult;
import com.example.fei.common.core.controller.BaseController;
import com.example.fei.common.core.page.TableDataInfo;
import com.example.fei.common.utils.OtherUtils;
import com.example.fei.common.utils.StringUtils;
import com.example.fei.domain.Goods;
import com.example.fei.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询
     *  demo:  http://localhost:8080/goods/list?pageNum=1&pageSize=5
     * @param goods {}
     * @return {
     *          "total": 15,
     *          "rows": [],
     *          "code": 200,
     *          "msg": "查询成功"
     *       }
     */
    @GetMapping("/list")
    public TableDataInfo list(Goods goods) {
        startPage();
        List<Goods> goodsList = goodsService.selectGoodsList(goods);
        return getDataTable(goodsList);
    }

    @GetMapping("/detail")
    public AjaxResult detail(long id) {
        Goods goods = goodsService.selectGoods(id);

        AjaxResult ajax = AjaxResult.success("success");
        ajax.put(AjaxResult.DATA_TAG, goods);
        // if (true) {}
        return ajax;

    }

    @PostMapping("add")
    public AjaxResult add(@RequestBody Goods goods) throws NoSuchAlgorithmException {

        Long goodsNumber = goods.getGoodsNumber();
        BigDecimal unitPrice = goods.getUnitPrice();

        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdFormatter.format(nowTime);
        String substring = StringUtils.substring(OtherUtils.getHashStr(formatDate), 0, 4);


        goods.setCountPrice(unitPrice.multiply(BigDecimal.valueOf(goodsNumber)));
        goods.setGoodsNumType(formatDate + substring);

        // int rowNum = goodsService.insertGoods(goods);
        // AjaxResult ajax = AjaxResult.success("success");
        // ajax.put("rows", rowNum); // 大于零,说明成功
        // return ajax;
        return toAjax(goodsService.insertGoods(goods));
    }

    @PostMapping("update")
    public AjaxResult update(@RequestBody Goods goods) throws NoSuchAlgorithmException {
        Long goodsNumber = goods.getGoodsNumber();
        BigDecimal unitPrice = goods.getUnitPrice();

        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdFormatter.format(nowTime);
        String substring = StringUtils.substring(OtherUtils.getHashStr(formatDate), 0, 4);


        goods.setCountPrice(unitPrice.multiply(BigDecimal.valueOf(goodsNumber)));
        goods.setGoodsNumType(formatDate + substring);

        return toAjax(goodsService.updateGoods(goods));
    }

    @GetMapping("del")
    public AjaxResult del(String ids) {
        return toAjax(goodsService.delGoods(ids));
    }

}
