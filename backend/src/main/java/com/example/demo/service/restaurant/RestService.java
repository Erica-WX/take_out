package com.example.demo.service.restaurant;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */
public interface RestService {
    String register(String restName, String address, String type);

    String getID();
}
