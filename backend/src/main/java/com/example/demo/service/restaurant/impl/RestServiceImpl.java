package com.example.demo.service.restaurant.impl;

import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.restaurant.RestService;
import com.example.demo.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Service
public class RestServiceImpl implements RestService {

    private RestRepository restRepository;

    @Autowired
    public RestServiceImpl(RestRepository restRepository){
        this.restRepository = restRepository;
    }

    @Override
    public String register(String restName, String address, String type) {

        String id = getID();
        Restaurant restaurant = new Restaurant(id, restName, address, type, false);
        restRepository.save(restaurant);
        return id;
    }

    @Override
    public String getID() {
        String randomCode = RandomCodeUtil.generateUniqueCode();
        String id = randomCode.substring(0,7);
        return id;
    }
}
