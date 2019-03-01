package com.example.demo.payloads.order;

import com.example.demo.entity.FoodInfo;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public class NewOrderRequest {

    private String email;

    private String restId;

    private double sum;

    private List<FoodInfo> foodList;

    private double disByLevel;

    private double disByRest;

    private double fullMoney;

    private int aid;

    public String getEmail() {
        return email;
    }

    public String getRestId() {
        return restId;
    }

    public double getSum() {
        return sum;
    }

    public List<FoodInfo> getFoodList() {
        return foodList;
    }

    public double getDisByLevel() {
        return disByLevel;
    }

    public double getDisByRest() {
        return disByRest;
    }

    public double getFullMoney() {
        return fullMoney;
    }

    public int getAid() {
        return aid;
    }
}
