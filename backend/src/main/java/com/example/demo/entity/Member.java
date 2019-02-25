package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@Entity
public class Member {

    @Id
    private String email;

    private String username;

    private String password;

    private String phone;

    private int level;

    private double score;

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

    public double getScore() {
        return score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Member(String email, String username, String password, String phone, int level, double score, boolean usable) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.level = level;
        this.score = score;
        this.usable = usable;
    }

    public Member(){

    }
}
