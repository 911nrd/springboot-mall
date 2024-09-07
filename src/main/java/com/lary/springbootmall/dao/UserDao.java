package com.lary.springbootmall.dao;

import com.lary.springbootmall.dto.UserRegisterRequest;
import com.lary.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);


}
