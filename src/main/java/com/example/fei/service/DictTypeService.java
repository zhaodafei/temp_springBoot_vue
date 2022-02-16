package com.example.fei.service;

import com.example.fei.domain.DictType;

import java.util.List;

public interface DictTypeService {
    public List<DictType> selectDictTypeList(DictType dictType);

    public DictType selectDictTypeDetail(Long dictId);

    public int delDictType(String dictIds);

    public int updateDictType(DictType dictType);

    public int insertDictType(DictType dictType);
}
