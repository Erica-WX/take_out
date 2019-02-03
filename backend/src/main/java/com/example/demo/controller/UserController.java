package com.example.demo.controller;

import com.example.demo.payloads.user.MemberRegisterRequest;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){this.userService = userService;}

    @PostMapping("/register")
    public boolean register(@RequestBody MemberRegisterRequest memberRegisterRequest) {

        return userService.register(memberRegisterRequest.getUsername(), memberRegisterRequest.getPassword(), memberRegisterRequest.getEmail());

    }

    @GetMapping("/login")
    public boolean login(){
        return false;
    }
}
