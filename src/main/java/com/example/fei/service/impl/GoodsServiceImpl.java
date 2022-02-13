package com.example.fei.service.impl;

import com.example.fei.domain.Goods;
import com.example.fei.mapper.GoodsMapper;
import com.example.fei.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Goods selectGoods(long goodsId) {
        return goodsMapper.selectGoods(goodsId);
    }

    @Override
    public List<Goods> selectGoodsList(Goods goods) {
        return goodsMapper.selectGoodsList(goods);
    }

    @Override
    public Goods selectBudget(Goods goods) {
        return goodsMapper.selectBudget(goods);
    }

    @Override
    public int delGoods(String ids) {
        return goodsMapper.delGoods(ids);
    }

    @Override
    public int insertGoods(Goods goods) {
        int row = goodsMapper.insertGoods(goods);
        if (row > 0) {

        }
        return row;
    }

}
