package com.example.demo.util;

import java.util.UUID;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/11
 */
public class RandomCodeUtil {

    public static String generateUniqueCode() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
