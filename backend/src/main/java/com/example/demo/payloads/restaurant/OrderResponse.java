package com.example.demo.payloads.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public class OrderResponse {

    @JsonProperty("restId")
    private String restId;

    @JsonProperty("sum")
    private double sum;

    @JsonProperty("fullMoney")
    private double fullMoney;

    @JsonProperty("disMoneyByRest")
    private double disMoneyByRest;

    @JsonProperty("disMoneyByLevel")
    private double disMoneyByLevel;

    public OrderResponse(String restId, double sum, double fullMoney, double disMoneyByRest, double disMoneyByLevel) {
        this.restId = restId;
        this.sum = sum;
        this.fullMoney = fullMoney;
        this.disMoneyByRest = disMoneyByRest;
        this.disMoneyByLevel = disMoneyByLevel;
    }
}
