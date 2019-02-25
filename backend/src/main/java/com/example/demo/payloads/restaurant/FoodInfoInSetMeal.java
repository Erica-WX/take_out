package com.example.demo.payloads.restaurant;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public class FoodInfoInSetMeal {
    private int id;

    private String name;

    private double price;

    private int num;

    public FoodInfoInSetMeal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
