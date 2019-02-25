package com.example.demo.payloads.user;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public class EditMemberInfoRequest {

    private String username;

    private String email;

    private String phone;

    private List<AddressInfo> addressList;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<AddressInfo> getAddressList() {
        return addressList;
    }
}
