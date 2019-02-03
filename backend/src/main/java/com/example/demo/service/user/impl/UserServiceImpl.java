package com.example.demo.service.user.impl;

import com.example.demo.dao.user.MemberRepository;
import com.example.demo.entity.Member;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean register(String username, String password, String email) {

        Optional<Member> member = memberRepository.findByEmail(email);
        if(!member.isPresent()){
            //即表示email不存在
            Member newMember = new Member(email,username,password,1,true);
            memberRepository.save(newMember);
            return true;
        }

        return false;
    }

    @Override
    public boolean login(String email, String password){
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()){
            if(member.get().getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }
}
