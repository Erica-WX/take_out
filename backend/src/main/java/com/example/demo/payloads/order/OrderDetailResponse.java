package com.example.demo.payloads.order;

import com.example.demo.payloads.restaurant.FoodListResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/27
 */
public class OrderDetailResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("sum")
    private double sum;

    @JsonProperty("disByLevel")
    private double disByLevel;

    @JsonProperty("disByRest")
    private double disByRest;

    @JsonProperty("fullMoney")
    private double fullMoney;

    @JsonProperty("foodList")
    private List<FoodListResponse> foodList;

    @JsonProperty("isCancel")
    private boolean isCancel;

   public OrderDetailResponse(int id, double sum, double disByLevel, double disByRest, double fullMoney, List<FoodListResponse> foodList, boolean isCancel) {
        this.id = id;
        this.sum = sum;
        this.disByLevel = disByLevel;
        this.disByRest = disByRest;
        this.fullMoney = fullMoney;
        this.foodList = foodList;
        this.isCancel = isCancel;
    }

    public OrderDetailResponse() {
    }
}
