package com.example.demo.service.member;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

public interface MemberService {

    boolean register(String username, String password, String email);
    String login(String username, String password);
    boolean verify(String code);
}
