package com.example.fei.service;

import com.example.fei.domain.BillCount;

import java.util.List;

public interface BillCountService {

    public List<BillCount> selectBillList(BillCount billCount);

    public int delBill(String ids);

    public int insertBill(BillCount billCount);
}
