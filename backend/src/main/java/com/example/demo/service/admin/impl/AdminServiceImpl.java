package com.example.demo.service.admin.impl;

import com.example.demo.dao.admin.AdminRepository;
import com.example.demo.dao.restaurant.ModifyInfoRepository;
import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.Admin;
import com.example.demo.entity.ModifyInfo;
import com.example.demo.entity.Restaurant;
import com.example.demo.payloads.admin.ApproveAbstractResponse;
import com.example.demo.payloads.admin.ModifyInfoResponse;
import com.example.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private ModifyInfoRepository modifyInfoRepository;
    private RestRepository restRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            ModifyInfoRepository modifyInfoRepository,
                            RestRepository restRepository) {
        this.adminRepository = adminRepository;
        this.modifyInfoRepository = modifyInfoRepository;
        this.restRepository = restRepository;
    }

    @Override
    public boolean login(String adminName, String password) {

        Optional<Admin> optional = adminRepository.findByUsername(adminName);
        if(optional.isPresent()) {
            Admin admin = optional.get();
            String pw = admin.getPassword();
            if(pw.equals(password)) {
                return true;
            }else {
                return false;
            }
        }

        return false;
    }

    @Override
    public List<ApproveAbstractResponse> getAbstractList() {

        List<ModifyInfo> modifyInfos = modifyInfoRepository.getAll();

        ArrayList<ApproveAbstractResponse> list = new ArrayList<>();
        for(ModifyInfo m: modifyInfos) {
            int mid = m.getId();
            String restName = m.getRestName();
            LocalDateTime requestTime = m.getRequestTime();

            ApproveAbstractResponse response = new ApproveAbstractResponse(mid, restName, requestTime);
            list.add(response);
        }

        return list;
    }

    @Override
    public ModifyInfoResponse getModifyInfo(int mid) {

        ModifyInfo modifyInfo = modifyInfoRepository.findById(mid).get();
        ModifyInfoResponse response = new ModifyInfoResponse(modifyInfo.getRestaurant().getId(),
                modifyInfo.getRestName(),
                modifyInfo.getDistrict(),
                modifyInfo.getAddress(),
                modifyInfo.getType());

        return response;
    }

    @Override
    public void approveModify(int mid) {
        ModifyInfo modifyInfo = modifyInfoRepository.findById(mid).get();
        Restaurant rest = modifyInfo.getRestaurant();

        String name = modifyInfo.getRestName();
        String district = modifyInfo.getDistrict();
        String address = modifyInfo.getAddress();
        String type = modifyInfo.getType();

        rest.setName(name);
        rest.setDistrict(district);
        rest.setAddress(address);
        rest.setType(type);
        restRepository.save(rest);

        modifyInfoRepository.delete(modifyInfo);

    }

    @Override
    public void rejectModify(int mid) {
        ModifyInfo modifyInfo = modifyInfoRepository.findById(mid).get();
        modifyInfoRepository.delete(modifyInfo);
    }
}
