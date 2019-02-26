package com.example.demo.payloads.restaurant;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public class CalOrderRequest {

    private String restId;

    private int level;

    private double sum;

    public CalOrderRequest() {
    }

    public CalOrderRequest(String restId, int level, double sum) {
        this.restId = restId;
        this.level = level;
        this.sum = sum;
    }

    public String getRestId() {
        return restId;
    }

    public int getLevel() {
        return level;
    }

    public double getSum() {
        return sum;
    }
}
