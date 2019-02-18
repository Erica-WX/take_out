package com.example.demo.service.restaurant.impl;

import com.example.demo.service.restaurant.TimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/18
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public String getPredictTime() {

        LocalDateTime nowTime  = LocalDateTime.now();
        LocalDateTime time = nowTime.plusMinutes(30);

        int hour = time.getHour();
        int minute = time.getMinute();

        return hour+":"+minute;
    }
}
