package com.bin.myblog.dao;

import com.bin.myblog.common.Page;
import com.bin.myblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    Integer saveType(Type type);

    Type getTypeById(Long id);

    List<Type> listType(@Param("startIndex") Integer startIndex , @Param("currentCount") Integer currentCount);

    Integer updateType(Type type);

    Integer deleteType(Long id);

    Long selectTypeCount();

    Type getTypeByName(String typename);

    List<Type> getAllType();

    Integer countType();
}
