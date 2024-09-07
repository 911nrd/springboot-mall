package com.lary.springbootmall.service.impl;

import com.lary.springbootmall.dao.UserDao;
import com.lary.springbootmall.dto.UserRegisterRequest;
import com.lary.springbootmall.model.User;
import com.lary.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
