package com.bin.myblog.dao;

import com.bin.myblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    List<User> getAllUser();

    User checkLoginUser(String username);

    User getUserById(Long id);

}
