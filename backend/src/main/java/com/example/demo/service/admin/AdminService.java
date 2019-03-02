package com.example.demo.service.admin;

import com.example.demo.payloads.admin.AdminMemberResponse;
import com.example.demo.payloads.admin.AdminRestResponse;
import com.example.demo.payloads.admin.ApproveAbstractResponse;
import com.example.demo.payloads.admin.ModifyInfoResponse;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public interface AdminService {

    boolean login(String adminName, String password);

    List<ApproveAbstractResponse> getAbstractList();

    ModifyInfoResponse getModifyInfo(int mid);

    void approveModify(int mid);

    void rejectModify(int mid);

    AdminMemberResponse getMemberInfo();

    AdminRestResponse getRestInfo();

    double getEarning();

    double[] getEarningList(String year);
}
