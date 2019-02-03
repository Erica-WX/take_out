package com.example.demo.payloads.user;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */
public class MemberLoginRequest {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
