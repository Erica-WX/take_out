package com.example.demo.payloads.restaurant;

import java.time.LocalDate;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public class NewFoodRequest {
    private String restId;

    private String name;

    private String type;

    private double price;

    private int amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private String image;

    public String getRestId() {
        return restId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getImage() {
        return image;
    }
}
