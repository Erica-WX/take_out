package com.example.demo.service.user.impl;

import com.example.demo.entity.Member;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/11
 */
public class RegisterMemberMap extends HashMap<String, Member> {
    public void setTime(final String randomCode) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Member member = get(randomCode);
                if (member != null) {
                    remove(randomCode);
                }
            }
        };
        timer.schedule(timerTask, 3600000);
    }
}
