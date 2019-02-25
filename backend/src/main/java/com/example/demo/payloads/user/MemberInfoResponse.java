package com.example.demo.payloads.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public class MemberInfoResponse {

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("level")
    private int level;

    @JsonProperty("score")
    private double score;

    @JsonProperty("addressList")
    private List<AddressInfo> addressList;

    public MemberInfoResponse(String email, String username, String phone, int level, double score, List<AddressInfo> addressList) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.level = level;
        this.score = score;
        this.addressList = addressList;
    }
}
