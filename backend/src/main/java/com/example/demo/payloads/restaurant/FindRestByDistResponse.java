package com.example.demo.payloads.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/17
 */
public class FindRestByDistResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("address")
    private String address;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
