package com.example.demo.service.member.impl;

import com.example.demo.dao.member.AddressRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.Member;
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
    public List<String> getAllAddress(String email) {

        Member member = new Member();
        member.setEmail(email);

        List<Address> addresses = addressRepository.findAllByMember(member);
        ArrayList<String> addressList = new ArrayList<>();

        if(addresses != null) {
            for(Address a: addresses) {
                addressList.add(a.getAddress());
            }
        }

        return addressList;
    }

    @Override
    public boolean addNewAddress(String email, String address) {

        Member member = new Member();
        member.setEmail(email);

        List<Address> addresses = addressRepository.findAllByMember(member);
        for(Address a: addresses) {
            if(a.getAddress().equals(address)){
                return false;
            }
        }
        Address newAddress = new Address(member, address);
        addressRepository.save(newAddress);
        return true;
    }
}
