package com.example.demo.payloads.order;

import com.example.demo.payloads.restaurant.FoodListResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/28
 */
public class MemberStatisticsResponse {

    @JsonProperty("restName")
    private String restName;

    @JsonProperty("restType")
    private String restType;

    @JsonProperty("orderTime")
    private LocalDateTime orderTime;

    @JsonProperty("foodList")
    private List<FoodListResponse> foodList;

    @JsonProperty("sum")
    private double sum;

    @JsonProperty("isCancel")
    private boolean isCancel;

    public MemberStatisticsResponse() {
    }

    public MemberStatisticsResponse(String restName, String restType, LocalDateTime orderTime, List<FoodListResponse> foodList, double sum, boolean isCancel) {
        this.restName = restName;
        this.restType = restType;
        this.orderTime = orderTime;
        this.foodList = foodList;
        this.sum = sum;
        this.isCancel = isCancel;
    }
}
