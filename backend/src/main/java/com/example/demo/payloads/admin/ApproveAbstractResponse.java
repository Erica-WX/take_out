package com.example.demo.payloads.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public class ApproveAbstractResponse {

    @JsonProperty("mid")
    private int mid;

    @JsonProperty("restName")
    private String restName;

    @JsonProperty("requestTime")
    private LocalDateTime requestTime;

    public ApproveAbstractResponse() {
    }

    public ApproveAbstractResponse(int mid, String restName, LocalDateTime requestTime) {
        this.mid = mid;
        this.restName = restName;
        this.requestTime = requestTime;
    }
}
