package com.bin.myblog.service.serviceImpl;

import com.bin.myblog.dao.UserMapper;
import com.bin.myblog.pojo.User;
import com.bin.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
       return userMapper.getAllUser();
    }

    @Override
    public User checkLoginUser(String username) {
        return userMapper.checkLoginUser(username);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
