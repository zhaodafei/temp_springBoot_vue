package com.example.fei.mapper;

import com.example.fei.domain.DictData;

import java.util.List;

public interface DictDataMapper {
    public List<DictData> selectDictTypeList(DictData dictData);

    public List<DictData> queryDictDataByType(String dictType);

    public DictData selectDictDataById(String dictCode);

    public int updateByDictCode(String dictCodes); // updateByDictCode

    public int updateDictData(DictData dictData);

    public int insertDictData(DictData dictData);
}
