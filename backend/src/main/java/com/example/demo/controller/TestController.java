package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/1/28
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/str")
    public String getStr(){
        System.out.println("in get string.");
        return "Test";
    }
}
