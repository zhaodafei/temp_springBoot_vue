package com.example.fei.service.impl;

import com.example.fei.domain.BillCount;
import com.example.fei.mapper.BillCountMapper;
import com.example.fei.service.BillCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

// @Service("BillCountService")
@Service
public class BillCountImpl implements BillCountService {
    @Resource
    private BillCountMapper billCountMapper;

    @Override
    public List<BillCount> selectBillList(BillCount billCount) {
        return billCountMapper.selectBillList(billCount);
    }

    @Override
    public int delBill(String ids) {
        return billCountMapper.delBill(ids);
    }

    @Override
    public int insertBill(BillCount billCount) {
        return billCountMapper.insertBill(billCount);
    }
}
