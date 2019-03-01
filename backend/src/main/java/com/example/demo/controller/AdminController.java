package com.example.demo.controller;

import com.example.demo.payloads.admin.ApproveAbstractResponse;
import com.example.demo.payloads.admin.ModifyInfoResponse;
import com.example.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public boolean login(String adminName, String password) {return adminService.login(adminName, password);}

    @PostMapping("/get_abstract")
    public List<ApproveAbstractResponse> getAbstractList() {return adminService.getAbstractList();}

    @GetMapping("/get_modify_info")
    public ModifyInfoResponse getModifyInfo(int mid) {return adminService.getModifyInfo(mid);}

    @GetMapping("approve_modify")
    public void approveModify(int mid) {adminService.approveModify(mid);}

    @GetMapping("/reject_modify")
    public void rejectModify(int mid) {adminService.rejectModify(mid);}
}
