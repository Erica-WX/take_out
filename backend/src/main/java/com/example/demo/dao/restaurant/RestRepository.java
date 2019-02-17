package com.example.demo.dao.restaurant;

import com.example.demo.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */
public interface RestRepository extends CrudRepository<Restaurant, String> {

    @Override
    Optional<Restaurant> findById(String id);

    List<Restaurant> findByDistrict(String district);
}
