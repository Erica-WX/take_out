package com.example.demo.service.user;

import org.springframework.stereotype.Service;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

public interface UserService {

    boolean register(String username, String password, String email);
    boolean login(String username, String password);
    boolean verify(String code);
}
