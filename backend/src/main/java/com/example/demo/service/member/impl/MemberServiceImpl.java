package com.example.demo.service.member.impl;

import com.example.demo.dao.member.MemberRepository;
import com.example.demo.entity.Member;
import com.example.demo.service.mail.MailService;
import com.example.demo.service.member.MemberService;
import com.example.demo.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@Service
public class MemberServiceImpl implements MemberService {


    private MemberRepository memberRepository;

    private MailService mailService;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             MailService mailService) {
        this.memberRepository = memberRepository;
        this.mailService = mailService;
    }

    private static RegisterMemberMap registerMemberMap = new RegisterMemberMap();

    @Override
    public boolean register(String username, String password, String email) {

        Optional<Member> member = memberRepository.findByEmail(email);
        if(!member.isPresent()){
            //即表示email不存在
            Member newMember = new Member(email,username,password,1,0,true);

            String randomCode = RandomCodeUtil.generateUniqueCode();
            registerMemberMap.put(randomCode, newMember);
            registerMemberMap.setTime(randomCode);

            mailService.sendMail(email, randomCode);

            return true;
        }

        return false;
    }

    @Override
    public String login(String email, String password){
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()){
            if(member.get().getPassword().equals(password)){
                return member.get().getUsername();
            }
        }

        return "";
    }

    @Override
    public boolean verify(String code) {
        Member member = registerMemberMap.get(code);

        if(member != null) {
            memberRepository.save(member);
            registerMemberMap.remove(code);
            return true;
        }
        return false;
    }
}
