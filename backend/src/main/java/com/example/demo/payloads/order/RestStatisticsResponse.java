package com.example.demo.payloads.order;

import com.example.demo.payloads.restaurant.FoodListResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public class RestStatisticsResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("sendAddress")
    private String sendAddress;

    @JsonProperty("orderTime")
    private LocalDateTime orderTime;

    @JsonProperty("foodList")
    private List<FoodListResponse> foodList;

    @JsonProperty("sum")
    private double sum;

    @JsonProperty("isCancel")
    private boolean isCancel;

    @JsonProperty("earning")
    private double earning;

    public RestStatisticsResponse() {
    }

    public RestStatisticsResponse(String username, String sendAddress, LocalDateTime orderTime, List<FoodListResponse> foodList, double sum, boolean isCancel, double earning) {
        this.username = username;
        this.sendAddress = sendAddress;
        this.orderTime = orderTime;
        this.foodList = foodList;
        this.sum = sum;
        this.isCancel = isCancel;
        this.earning = earning;
    }
}
