package com.example.demo.controller;

import com.example.demo.payloads.user.MemberRegisterRequest;
import com.example.demo.service.member.AddressService;
import com.example.demo.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */

@RestController
@RequestMapping("/user")
public class MemberController {

    private MemberService memberService;
    private AddressService addressService;

    public MemberController(MemberService memberService, AddressService addressService){
        this.memberService = memberService;
        this.addressService = addressService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody MemberRegisterRequest memberRegisterRequest) {

        return memberService.register(memberRegisterRequest.getUsername(), memberRegisterRequest.getPassword(), memberRegisterRequest.getEmail());

    }

    @GetMapping("/login")
    public String login(String email, String password){
       return memberService.login(email, password);
    }

    @RequestMapping("/verify")
    public void verify(HttpServletResponse response, @RequestParam("code") String code) throws Exception {

        if(memberService.verify(code)) {
            response.sendRedirect("http://localhost:3000/#/login");
        }else {
            response.sendRedirect("http://localhost:3000/#/register");
        }
    }

    @GetMapping("/get_address")
    public List<String> getAllAddress(String email) {
        return addressService.getAllAddress(email);
    }

    @GetMapping("/new_address")
    public boolean addAddress(String email, String address){return addressService.addNewAddress(email, address);}
}
