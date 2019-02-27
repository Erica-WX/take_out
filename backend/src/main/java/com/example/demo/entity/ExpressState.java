package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/27
 */

@Entity
public class ExpressState {

    @Id
    private int oid;

    private String state;

    public ExpressState() {
    }

    public ExpressState(int oid, String state) {
        this.oid = oid;
        this.state = state;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
