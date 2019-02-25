package com.example.demo.service.restaurant;

import com.example.demo.entity.Restaurant;
import com.example.demo.payloads.restaurant.*;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */
public interface RestService {
    String register(String restName, String district, String address, String type);

    String getID();

    Restaurant login(String id);

    void addNewFood(NewFoodRequest foodRequest);

    List<FindRestByDistResponse> getRestByDistric(String district);

    List<FoodListResponse> getFoodList(String id);

    void setNewSetMeal(NewSetMealRequest setMealRequest);

    void setDiscount(DiscountInfoResponse discount);

    List<DiscountInfoResponse> getDiscountList(String restId);
}
