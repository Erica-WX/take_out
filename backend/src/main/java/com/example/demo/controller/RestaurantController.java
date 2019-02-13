package com.example.demo.controller;

import com.example.demo.payloads.restaurant.RestRegisterRequest;
import com.example.demo.service.restaurant.RestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@RequestBody RestRegisterRequest restRegisterRequest){return restService.register(restRegisterRequest.getName(),restRegisterRequest.getAddress(), restRegisterRequest.getType());}
}
