package com.example.demo.dao.restaurant;

import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public interface FoodRepository extends CrudRepository<Food, Integer> {

    List<Food> findByRest(Restaurant restaurant);
}
