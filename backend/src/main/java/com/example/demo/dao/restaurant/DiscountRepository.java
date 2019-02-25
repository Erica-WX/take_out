package com.example.demo.dao.restaurant;

import com.example.demo.entity.Discount;
import com.example.demo.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public interface DiscountRepository extends CrudRepository<Discount, Integer> {

    List<Discount> findByRestaurant(Restaurant restaurant);
}
