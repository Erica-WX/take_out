package com.example.demo.dao.member;

import com.example.demo.entity.Address;
import com.example.demo.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {
    List<Address> findAllByMember(Member member);
}
