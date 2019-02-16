package com.example.demo.dao.restaurant;

import com.example.demo.entity.Food;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public interface FoodRepository extends CrudRepository<Food, Integer> {
}
