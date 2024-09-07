package com.lary.springbootmall.service;

import com.lary.springbootmall.dto.UserRegisterRequest;
import com.lary.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);


}
