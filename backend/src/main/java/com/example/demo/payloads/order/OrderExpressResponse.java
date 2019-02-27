package com.example.demo.payloads.order;

import com.example.demo.entity.FoodInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/27
 */
public class OrderExpressResponse {

    @JsonProperty("oid")
    private int oid;

    @JsonProperty("orderTime")
    private LocalDateTime orderTime;

    @JsonProperty("sum")
    private double sum;

    @JsonProperty("foodList")
    private List<FoodInfo> foodList;

    @JsonProperty("state")
    private String state;

    public OrderExpressResponse() {
    }

    public OrderExpressResponse(int oid, LocalDateTime orderTime, double sum, List<FoodInfo> foodList, String state) {
        this.oid = oid;
        this.orderTime = orderTime;
        this.sum = sum;
        this.foodList = foodList;
        this.state = state;
    }
}
