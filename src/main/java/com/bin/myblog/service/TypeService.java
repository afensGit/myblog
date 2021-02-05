package com.bin.myblog.service;

import com.bin.myblog.pojo.Type;

import java.util.List;

public interface TypeService {

    Integer saveType(Type type);

    Type getTypeById(Long id);

    List<Type> listType(Integer startIndex , Integer currentCount);

    List<Type> getAllType();

    Integer updateType(Type type);

    Integer deleteType(Long id);

    Type getTypeByName(String typename);

    Integer countType();
}
