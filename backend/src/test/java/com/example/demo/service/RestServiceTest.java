package com.example.demo.service;

import com.example.demo.payloads.restaurant.CalOrderRequest;
import com.example.demo.service.restaurant.RestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestServiceTest {

    private RestService restService;

    @Autowired
    public void setRestService(RestService restService) {
        this.restService = restService;
    }

    @Test
    public void getDiscountTest() {
        CalOrderRequest request = new CalOrderRequest("6293602", 1, 10);
        restService.calOrder(request);
    }
}
