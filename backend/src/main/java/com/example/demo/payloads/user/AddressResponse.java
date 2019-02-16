package com.example.demo.payloads.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import sun.plugin2.message.Serializer;

import java.io.Serializable;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public class AddressResponse{

    @JsonProperty("district")
    private String district;

    @JsonProperty("address")
    private String address;

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
