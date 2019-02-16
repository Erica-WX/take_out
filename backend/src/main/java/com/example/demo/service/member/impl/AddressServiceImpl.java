package com.example.demo.service.member.impl;

import com.example.demo.dao.member.AddressRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.Member;
import com.example.demo.payloads.user.AddressResponse;
import com.example.demo.service.member.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressResponse> getAllAddress(String email) {

        Member member = new Member();
        member.setEmail(email);

        List<Address> addresses = addressRepository.findAllByMember(member);

        ArrayList<AddressResponse> addressList = new ArrayList<>();

        if(addresses != null){
            for(Address a: addresses) {
                AddressResponse response = new AddressResponse();
                response.setDistrict(a.getDistrict());
                response.setAddress(a.getAddress());
                addressList.add(response);
            }
        }

        return addressList;
    }

    @Override
    public boolean addNewAddress(String email, String district, String address) {

        Member member = new Member();
        member.setEmail(email);

        List<Address> addresses = addressRepository.findAllByMember(member);
        for(Address a: addresses) {
            if(a.getAddress().equals(address)){
                return false;
            }
        }
        Address newAddress = new Address(member, district, address);
        addressRepository.save(newAddress);
        return true;
    }
}
