package com.example.demo.dao;

import com.example.demo.dao.order.OrderRepository;
import com.example.demo.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersTest {

    private OrderRepository orderRepository;

    @Autowired
    public void  setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Test
    public void test(){
        Orders order = orderRepository.findById(1).get();
        order.setPaid(false);
        order.setValid(true);
        orderRepository.save(order);
    }
}
