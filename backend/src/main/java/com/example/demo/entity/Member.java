package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@Entity
public class Member {

    @Id
    @Email
    private String email;

    private String username;

    private String password;

    private int level;

    private boolean usable;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public boolean isUsable() {
        return usable;
    }

    public Member(@Email String email, String username, String password, int level, boolean usable) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.level = level;
        this.usable = usable;
    }

    public Member(){

    }
}
