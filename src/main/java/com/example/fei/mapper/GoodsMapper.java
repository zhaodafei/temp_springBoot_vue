package com.example.fei.mapper;

import com.example.fei.domain.BillCount;
import com.example.fei.domain.Goods;

import java.util.List;

public interface GoodsMapper {
    // 查询一条数据
    public Goods selectGoods(long id);

    public List<Goods> selectGoodsList(Goods goods);

    // 主要使用参数 startTime endTime
    public Goods selectBudget(Goods goods);

    public int delGoods(String ids);

    public int updateGoods(Goods goods);

    public int insertGoods(Goods goods);
}

