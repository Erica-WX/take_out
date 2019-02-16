package com.example.demo.controller;

import com.example.demo.entity.Restaurant;
import com.example.demo.payloads.restaurant.NewFoodRequest;
import com.example.demo.payloads.restaurant.RestRegisterRequest;
import com.example.demo.service.restaurant.RestService;
import org.springframework.web.bind.annotation.*;

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
}
