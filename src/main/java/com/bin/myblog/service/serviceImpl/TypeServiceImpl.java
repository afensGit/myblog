package com.bin.myblog.service.serviceImpl;

import com.bin.myblog.dao.TypeMapper;
import com.bin.myblog.pojo.Type;
import com.bin.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Integer saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType(Integer startIndex, Integer currentCount) {
        return typeMapper.listType(startIndex,currentCount);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public Integer updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public Integer deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public Type getTypeByName(String typename) {
        return typeMapper.getTypeByName(typename);
    }

    @Override
    public Integer countType() {
        return typeMapper.countType();
    }
}
