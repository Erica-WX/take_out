package com.example.demo.util;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/2
 */
public class OrderUtil {

    public static double getOrderPercent(String state) {

        double percent = 0;
        if(state.equals("等待商家接单")) {
            percent = 0;
        }else if(state.equals("等待商家发货")) {
            percent = 0.05;
        }else if(state.equals("配送中")) {
            percent = 0.1;
        }else if(state.equals("已送达")) {
            percent = 0.2;
        }

        return percent;
    }
}
