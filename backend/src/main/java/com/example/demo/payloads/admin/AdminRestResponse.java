package com.example.demo.payloads.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/2
 */
public class AdminRestResponse {

    @JsonProperty("num")
    private int num;

    @JsonProperty("restList")
    private int[] restList;

    public AdminRestResponse() {
    }

    public AdminRestResponse(int num, int[] restList) {
        this.num = num;
        this.restList = restList;
    }
}
