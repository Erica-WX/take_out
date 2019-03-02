package com.example.demo.payloads.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/2
 */
public class AdminMemberResponse {

    @JsonProperty("num")
    private int num;

    @JsonProperty("memberList")
    private int[] memberList;

    public AdminMemberResponse() {
    }

    public AdminMemberResponse(int num, int[] memberList) {
        this.num = num;
        this.memberList = memberList;
    }
}
