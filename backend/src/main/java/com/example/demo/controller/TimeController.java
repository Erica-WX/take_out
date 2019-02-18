package com.example.demo.controller;

import com.example.demo.service.restaurant.TimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/18
 */

@RestController
@RequestMapping("/time")
public class TimeController{

    private TimeService timeService;

    public TimeController(TimeService timeService){
        this.timeService = timeService;
    }

    @RequestMapping("get_time")
    public String getPredictTime(){return timeService.getPredictTime();}
}
