package com.example.demo.payloads.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public class GetOrderResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("orderTime")
    private LocalDateTime orderTime;

    @JsonProperty("sum")
    private double sum;

    public GetOrderResponse() {
    }

    public GetOrderResponse(int id, LocalDateTime orderTime, double sum) {
        this.id = id;
        this.orderTime = orderTime;
        this.sum = sum;
    }
}
