package com.example.fei.mapper;

import com.example.fei.domain.BillCount;

import java.util.List;

public interface BillCountMapper {

    public List<BillCount> selectBillList(BillCount billCount);

    public int delBill(String ids);

    public int insertBill(BillCount billCount);

}
