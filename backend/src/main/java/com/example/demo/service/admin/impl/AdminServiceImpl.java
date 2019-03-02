package com.example.demo.service.admin.impl;

import com.example.demo.dao.admin.AdminRepository;
import com.example.demo.dao.member.MemberRepository;
import com.example.demo.dao.order.ExpressStateRepository;
import com.example.demo.dao.order.OrderRepository;
import com.example.demo.dao.restaurant.ModifyInfoRepository;
import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.*;
import com.example.demo.payloads.admin.AdminMemberResponse;
import com.example.demo.payloads.admin.AdminRestResponse;
import com.example.demo.payloads.admin.ApproveAbstractResponse;
import com.example.demo.payloads.admin.ModifyInfoResponse;
import com.example.demo.service.admin.AdminService;
import com.example.demo.util.OrderUtil;
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
    private MemberRepository memberRepository;
    private OrderRepository orderRepository;
    private ExpressStateRepository expressStateRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            ModifyInfoRepository modifyInfoRepository,
                            RestRepository restRepository,
                            MemberRepository memberRepository,
                            OrderRepository orderRepository,
                            ExpressStateRepository expressStateRepository) {
        this.adminRepository = adminRepository;
        this.modifyInfoRepository = modifyInfoRepository;
        this.restRepository = restRepository;
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
        this.expressStateRepository = expressStateRepository;
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

    @Override
    public AdminMemberResponse getMemberInfo() {

        List<Member> members = memberRepository.getAll();

        int num = members.size();
        int[] member_list = new int[5];
        for(int i = 0; i < member_list.length; i++) {
            member_list[i] = 0;
        }
        for(Member m: members) {
            member_list[m.getLevel() - 1]++;
        }
        AdminMemberResponse response = new AdminMemberResponse(num, member_list);

        return response;
    }

    @Override
    public AdminRestResponse getRestInfo() {

        List<Restaurant> rests = restRepository.getAll();
        int num = rests.size();
        int[] restList = new int[4];

        for(int i = 0; i < restList.length; i++) {
            restList[i] = 0;
        }

        for(Restaurant r: rests) {
            if(r.getType().equals("甜品饮品")) {
                restList[0] ++;
            }else if(r.getType().equals("快餐便当")) {
                restList[1] ++;
            }else if(r.getType().equals("小吃夜宵")) {
                restList[2] ++;
            }else if(r.getType().equals("特色菜系")) {
                restList[3] ++;
            }
         }

         AdminRestResponse response = new AdminRestResponse(num, restList);

        return response;
    }

    @Override
    public double getEarning() {

        double earning = 0;
        List<Orders> orders = orderRepository.getAll();
        for(Orders o: orders) {
            if(!o.isCancel()) {
                if(o.isValid() && o.isPaid()) {
                    double sum = o.getSum();
                    earning += sum * 0.3;
                }
            }else {
                double sum = o.getSum();
                ExpressState expressState = expressStateRepository.findByOid(o.getId()).get();
                String state = expressState.getState();
                double percent = OrderUtil.getOrderPercent(state);
                earning += sum * percent * 0.3;
            }
        }

        earning = twoBitDouble(earning);

        return earning;
    }

    @Override
    public double[] getEarningList(String year) {
        System.out.println("year:" + year);

        List<Orders> orders = orderRepository.getAll();

        double[] earnings = new double[12];
        for(int i = 0; i < earnings.length; i++) {
            earnings[i] = 0;
        }

        for(Orders o: orders) {
            LocalDateTime orderTime = o.getOrderTime();

            String y = orderTime.getYear() + "";
            if(y.equals(year)) {
                if(!o.isCancel()) {
                    if (o.isValid() && o.isPaid()) {
                        double sum = o.getSum();
                        int month = orderTime.getMonthValue();
                        earnings[month - 1] += sum * 0.3;
                    }
                }else {
                    double sum = o.getSum();
                    ExpressState expressState = expressStateRepository.findByOid(o.getId()).get();
                    String state = expressState.getState();
                    double percent = OrderUtil.getOrderPercent(state);
                    int month = orderTime.getMonthValue();
                    earnings[month - 1] += sum * percent * 0.3;
                }
            }
        }

        return earnings;
    }


    private double twoBitDouble(double num) {
        return (double)Math.round(num * 100) / 100;
    }
}
