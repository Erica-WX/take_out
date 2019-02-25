package com.example.demo.controller;

import com.example.demo.entity.Restaurant;
import com.example.demo.payloads.restaurant.*;
import com.example.demo.service.restaurant.RestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@RestController
@RequestMapping("/rest")
public class RestaurantController {

    private RestService restService;

    public RestaurantController(RestService restService){
        this.restService = restService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RestRegisterRequest restRegisterRequest){return restService.register(restRegisterRequest.getName(), restRegisterRequest.getDistrict(), restRegisterRequest.getAddress(), restRegisterRequest.getType());}

    @GetMapping("/login")
    public Restaurant login(String id){return restService.login(id);}

    @PostMapping("/new_food")
    public void addNewFood(@RequestBody NewFoodRequest foodRequest){restService.addNewFood(foodRequest);}

    @GetMapping("/get_rests")
    public List<FindRestByDistResponse> getRestByDistrict(String district){return restService.getRestByDistric(district);}

    @GetMapping("/get_food_list")
    public List<FoodListResponse> getFoodList(String id){return restService.getFoodList(id);}

    @PostMapping("/new_setmeal")
    public void setNewSetMeal(@RequestBody NewSetMealRequest setMealRequest){restService.setNewSetMeal(setMealRequest);}

    @GetMapping("/get_discount_list")
    public List<DiscountInfoResponse> getDiscountList(String restId){return restService.getDiscountList(restId);}

    @PostMapping("/set_discount")
    public void setDiscount(@RequestBody DiscountInfoResponse discount){
        restService.setDiscount(discount);
    }
}
