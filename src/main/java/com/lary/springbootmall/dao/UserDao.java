package com.lary.springbootmall.dao;

import com.lary.springbootmall.dto.UserRegisterRequest;
import com.lary.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);




}
