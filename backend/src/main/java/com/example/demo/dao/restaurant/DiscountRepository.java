package com.example.demo.dao.restaurant;

import com.example.demo.entity.Discount;
import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/25
 */
public interface DiscountRepository extends CrudRepository<Discount, Integer> {

    List<Discount> findByRestaurant(Restaurant restaurant);

    @Query(value = "select * from discount d where d.rest_id = ?1 and d.start_date <= ?2 and d.end_date >= ?2", nativeQuery = true)
    List<Discount> getValidDiscount(String restId, LocalDate nowDate);
}
