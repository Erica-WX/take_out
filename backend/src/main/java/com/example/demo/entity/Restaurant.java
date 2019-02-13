package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Entity
public class Restaurant {

    @Id
    private String id;

    private String name;

    private String address;

    private String type;

    private boolean usable;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String address, String type, boolean usable) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.usable = usable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
