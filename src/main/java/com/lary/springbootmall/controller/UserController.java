package com.lary.springbootmall.controller;
import com.lary.springbootmall.dto.UserLoginRequest;
import com.lary.springbootmall.dto.UserRegisterRequest;

import com.lary.springbootmall.model.User;
import com.lary.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest)
    {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest)
    {
        User user = userService.login(userLoginRequest);//傳回user類型
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
}
