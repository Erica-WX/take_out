package com.example.demo.dao.order;

import com.example.demo.entity.Member;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public interface OrderRepository extends CrudRepository<Orders, Integer> {

    @Override
    Optional<Orders> findById(Integer integer);

    List<Orders> findByMember(Member member);

    List<Orders> findByRestaurant(Restaurant rest);

    @Query(value = "select * from orders o where o.email = ?1 and o.is_paid = false and o.is_valid = true", nativeQuery = true)
    List<Orders> getNotPaidList(String email);

    @Query(value = "select * from orders o where o.email = ?1 and o.is_paid = true and o.is_valid = true", nativeQuery = true)
    List<Orders> getCompleteList(String email);

    @Query(value = "select * from orders o where o.email = ?1 and o.is_valid = false", nativeQuery = true)
    List<Orders> getInvalidList(String email);

    @Query(value = "select * from orders o where o.rest_id = ?1 and o.is_paid = true and o.is_valid = true", nativeQuery = true)
    List<Orders> getPaidList(String restId);

    @Query(value = "select * from orders", nativeQuery = true)
    List<Orders> getAll();
}
