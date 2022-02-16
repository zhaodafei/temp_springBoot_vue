package com.example.fei.service.impl;

import com.example.fei.domain.DictType;
import com.example.fei.mapper.DictTypeMapper;
import com.example.fei.service.DictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DictTypeService")
public class DictTypeServiceImpl implements DictTypeService {
    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public DictType selectDictTypeDetail(Long dictId) {
        return dictTypeMapper.selectDictTypeDetail(dictId);
    }

    @Override
    public int delDictType(String dictIds) {
        return dictTypeMapper.delDictType(dictIds);
    }

    @Override
    public int updateDictType(DictType dictType) {
        return dictTypeMapper.updateDictType(dictType);
    }

    @Override
    public int insertDictType(DictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }
}
