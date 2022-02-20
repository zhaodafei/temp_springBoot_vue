package com.example.fei.service.impl;

import com.example.fei.domain.DictData;
import com.example.fei.mapper.DictDataMapper;
import com.example.fei.service.DictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DictDataService")
public class DictDataServiceImpl implements DictDataService {
    @Resource
    private DictDataMapper dictDataMapper;

    @Override
    public List<DictData> selectDictTypeList(DictData dictData) {
        return dictDataMapper.selectDictTypeList(dictData);
    }

    @Override
    public List<DictData> queryDictDataByType(String dictType) {
        return dictDataMapper.queryDictDataByType(dictType);
    }

    @Override
    public DictData selectDictDataById(String dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    @Override
    public int delDictType(String dictCodes) {
        return dictDataMapper.updateByDictCode(dictCodes);
    }

    @Override
    public int updateDictData(DictData dictData) {
        return dictDataMapper.updateDictData(dictData);
    }

    @Override
    public int insertDictData(DictData dictData) {
        return dictDataMapper.insertDictData(dictData);
    }
}
