package com.example.demo.service.member;

import com.example.demo.payloads.user.EditMemberInfoRequest;
import com.example.demo.payloads.user.MemberInfoResponse;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

public interface MemberService {

    boolean register(String username, String password, String email, String phone);
    String login(String username, String password);
    boolean verify(String code);
    MemberInfoResponse getInfo(String email);
    void saveInfo(EditMemberInfoRequest request);
    int getLevel(String email);
    void deleteMember(String email);
}
