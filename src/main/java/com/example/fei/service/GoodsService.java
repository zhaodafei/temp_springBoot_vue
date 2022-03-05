package com.example.fei.service;

import com.example.fei.domain.Goods;

import java.util.List;

public interface GoodsService {
    public Goods selectGoods(long goodsId);

    public List<Goods> selectGoodsList(Goods goods);

    public Goods selectBudget(Goods goods);

    public int delGoods(String ids);

    public int updateGoods(Goods goods);

    public int insertGoods(Goods goods);
}
