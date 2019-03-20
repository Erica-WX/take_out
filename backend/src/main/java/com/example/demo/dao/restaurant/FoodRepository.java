package com.example.demo.dao.restaurant;

import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public interface FoodRepository extends CrudRepository<Food, Integer> {


    List<Food> findByRest(Restaurant restaurant);

    @Query(value = "select * from food f where f.rest_id = ?1 and f.start_date <= ?2 and f.end_date >= ?2", nativeQuery = true)
    List<Food> getFoodByDate(String restId, LocalDate nowDate);

    Optional<Food> findById(int id);
}
