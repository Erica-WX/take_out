package com.example.demo.service.order;

import com.example.demo.entity.Orders;
import com.example.demo.payloads.order.NewOrderRequest;
import com.example.demo.payloads.order.GetOrderResponse;
import com.example.demo.payloads.order.OrderExpressResponse;
import com.example.demo.payloads.order.OrderDetailResponse;

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

    boolean payOrder(int oid);

    Orders cancelOrder(int oid);

    OrderDetailResponse getOrderDetail(int oid);

    String getExpressState(int oid);

    List<OrderExpressResponse> getNotReceiveOrders(String restId);

    List<OrderExpressResponse> getNotDeliverOrders(String restId);

    List<OrderExpressResponse> getDeliveredOrders(String restId);

    void receiveOrder(int oid);

    void deliverOrder(int oid);

    void acceptOrder(int oid);

}
