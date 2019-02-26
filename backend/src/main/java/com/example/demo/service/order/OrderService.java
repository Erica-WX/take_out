package com.example.demo.service.order;

import com.example.demo.payloads.order.NewOrderRequest;
import com.example.demo.payloads.order.GetOrderResponse;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
public interface OrderService {

    void addNewOrder(NewOrderRequest request);

    List<GetOrderResponse> getNotPaidOrders(String email);

    List<GetOrderResponse> getCompleteOrders(String email);

    List<GetOrderResponse> getInvalidOrders(String email);
}
