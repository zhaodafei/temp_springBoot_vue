package com.example.fei.service;

import com.example.fei.domain.DictData;

import java.util.List;

public interface DictDataService {
    public List<DictData> selectDictTypeList(DictData dictData);

    public List<DictData> queryDictDataByType(String dictType);

    public DictData selectDictDataById(String dictCode);

    public int delDictType(String dictCodes);

    public int updateDictData(DictData dictData);

    public int insertDictData(DictData dictData);
}
