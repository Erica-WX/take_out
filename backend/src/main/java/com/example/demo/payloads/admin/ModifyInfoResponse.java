package com.example.demo.payloads.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public class ModifyInfoResponse {

    @JsonProperty("restId")
    private String restId;

    @JsonProperty("restName")
    private String restName;

    @JsonProperty("district")
    private String district;

    @JsonProperty("address")
    private String address;

    @JsonProperty("type")
    private String type;

    public ModifyInfoResponse() {
    }

    public ModifyInfoResponse(String restId, String restName, String district, String address, String type) {
        this.restId = restId;
        this.restName = restName;
        this.district = district;
        this.address = address;
        this.type = type;
    }
}
