package com.example.demo.dao.restaurant;

import com.example.demo.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */
public interface RestRepository extends CrudRepository<Restaurant, String> {
}
