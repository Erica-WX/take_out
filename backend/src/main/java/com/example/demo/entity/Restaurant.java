package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

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

    private String district;

    private String address;

    private String type;

    private boolean usable;

   /* @OneToMany(cascade={CascadeType.MERGE},fetch= FetchType.LAZY)
    @JoinColumn(name="id")
    private Set<Food> foodSet;*/

    public Restaurant() {
    }

    public Restaurant(String id, String name, String district, String address, String type, boolean usable) {
        this.id = id;
        this.name = name;
        this.district = district;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    /*public Set<Food> getFoodSet() {
        return foodSet;
    }

    public void setFoodSet(Set<Food> foodSet) {
        this.foodSet = foodSet;
    }*/
}
