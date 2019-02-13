package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinColumn(name="email",referencedColumnName="email") // 外键设置为email
    private Member member;

    private String address;

    public Address(){

    }

    public Address(Member member, String address) {
        this.member = member;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
