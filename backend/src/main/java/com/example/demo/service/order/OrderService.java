package com.example.demo.service.order;

import com.example.demo.entity.Orders;
import com.example.demo.payloads.order.*;

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

    /**
     * 会员想要退订,修改订单isCancel和配送状态
     * @param oid
     */
    void setOrderCancel(int oid);

    /**
     * 商家同意退订,减库存，退款，改订单isValid和isPaid
     * @param oid
     */
    void agreeCancel(int oid);

    List<MemberStatisticsResponse> getMemberStatistics(String email);

}
