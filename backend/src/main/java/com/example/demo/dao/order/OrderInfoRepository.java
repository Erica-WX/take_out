package com.example.demo.dao.order;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public interface OrderInfoRepository extends CrudRepository<OrderInfo, Integer> {

    List<OrderInfo> findByOrder(Orders order);
}
