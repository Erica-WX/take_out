package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private int id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinColumn(name="restId") // 外键设置为rest_id
    private Restaurant restaurant;

    private double fullMoney;

    private double disMoney;

    private LocalDate startDate;

    private LocalDate endDate;

    public Discount() {
    }

    public Discount(@NotNull Restaurant restaurant, double fullMoney, double disMoney, LocalDate startDate, LocalDate endDate) {
        this.restaurant = restaurant;
        this.fullMoney = fullMoney;
        this.disMoney = disMoney;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(double fullMoney) {
        this.fullMoney = fullMoney;
    }

    public double getDisMoney() {
        return disMoney;
    }

    public void setDisMoney(double disMoney) {
        this.disMoney = disMoney;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
