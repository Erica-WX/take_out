package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private int id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinColumn(name="email") // 外键设置为email
    private Member member;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinColumn(name="restId") // 外键设置为rest_id
    private Restaurant restaurant;

    private double sum;

    private double disByLevel;

    private double disByRest;

    private double fullMoney;

    private LocalDateTime orderTime;

    private boolean isValid;

    private boolean isPaid;

    private boolean isCancel;

    public Orders() {
    }

    public Orders(@NotNull Member member, @NotNull Restaurant restaurant, double sum, double disByLevel, double disByRest, double fullMoney, LocalDateTime orderTime, boolean isValid, boolean isPaid, boolean isCancel) {
        this.member = member;
        this.restaurant = restaurant;
        this.sum = sum;
        this.disByLevel = disByLevel;
        this.disByRest = disByRest;
        this.fullMoney = fullMoney;
        this.orderTime = orderTime;
        this.isValid = isValid;
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getDisByLevel() {
        return disByLevel;
    }

    public void setDisByLevel(double disByLevel) {
        this.disByLevel = disByLevel;
    }

    public double getDisByRest() {
        return disByRest;
    }

    public void setDisByRest(double disByRest) {
        this.disByRest = disByRest;
    }

    public double getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(double fullMoney) {
        this.fullMoney = fullMoney;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }
}
