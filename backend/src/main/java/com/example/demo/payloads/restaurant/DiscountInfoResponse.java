package com.example.demo.payloads.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */

public class DiscountInfoResponse {

    private String restId;

    @JsonProperty("fullMoney")
    private double fullMoney;

    @JsonProperty("disMoney")
    private double disMoney;

    @JsonProperty("startDate")
    private LocalDate startDate;

    @JsonProperty("endDate")
    private LocalDate endDate;

    public DiscountInfoResponse() {
    }

    public DiscountInfoResponse(double fullMoney, double disMoney, LocalDate startDate, LocalDate endDate) {
        this.fullMoney = fullMoney;
        this.disMoney = disMoney;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setFullMoney(double fullMoney) {
        this.fullMoney = fullMoney;
    }

    public void setDisMoney(double disMoney) {
        this.disMoney = disMoney;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getFullMoney() {
        return fullMoney;
    }

    public double getDisMoney() {
        return disMoney;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }
}
