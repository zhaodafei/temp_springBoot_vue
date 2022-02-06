package com.example.fei.service.impl;

import com.example.fei.domain.User;
import com.example.fei.domain.User2;
import com.example.fei.mapper.UserMapper;
import com.example.fei.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUser(long userId) {
        return userMapper.selectUser(userId);
    }

    @Override
    public User selectUser2(long userId) {
        return userMapper.selectUser2(userId);
    }

    // @Override
    // public User selectUser3(long userId) {
    //     return userMapper.selectUser3(userId);
    // }

    @Override
    public User selectUser4(User user) {
        return userMapper.selectUser4(user);
    }

    @Override
    public User selectUser5(User user) {
        return userMapper.selectUser5(user);
    }

    @Override
    public User2 selectUser6(long userId) {
        return userMapper.selectUser6(userId);
    }

    @Override
    public User selectUser7(long userId) {
        return userMapper.selectUser7(userId);
    }

}
