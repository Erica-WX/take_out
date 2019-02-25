package com.example.demo.payloads.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public class AddressInfo {

    @JsonProperty("id")
    private int id;

    @JsonProperty("district")
    private String district;

    @JsonProperty("address")
    private String address;

    public AddressInfo() {
    }

    public AddressInfo(int id, String district, String address) {
        this.id = id;
        this.district = district;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }
}
