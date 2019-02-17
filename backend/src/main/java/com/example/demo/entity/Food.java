package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinColumn(name="restId") // 外键设置为rest_id
    private Restaurant rest;

    private String name;

    private String type;

    private double price;

    private int amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private String image;

    public Food() {
    }

    public Food(Restaurant rest, String name, String type, double price, int amount, LocalDate startDate, LocalDate endDate, String image) {
        this.rest = rest;
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
