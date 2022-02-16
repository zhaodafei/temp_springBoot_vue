package com.example.fei.mapper;

import com.example.fei.domain.DictType;

import java.util.List;

public interface DictTypeMapper {

    public List<DictType> selectDictTypeList(DictType dictType);

    public DictType selectDictTypeDetail(Long dictId);

    public int delDictType(String dictIds);

    public int updateDictType(DictType dictType);

    public int insertDictType(DictType dictType);
}
