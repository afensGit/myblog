package com.bin.myblog.service;

import com.bin.myblog.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUser();

    User checkLoginUser(String username);

    User getUserById(Long id);
}
