package com.example.demo.payloads.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/17
 */
public class FoodListResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    private double price;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("image")
    private String image;

    public FoodListResponse(String name, String type, double price, int amount, String image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
