package com.example.demo.controller;

import com.example.demo.payloads.order.*;
import com.example.demo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new_order")
    public void addNewOrder(@RequestBody NewOrderRequest request){orderService.addNewOrder(request);}

    @GetMapping("/get_not_paid")
    public List<GetOrderResponse> getNotPaidList(String email) {return orderService.getNotPaidOrders(email);}

    @GetMapping("/get_complete")
    public List<GetOrderResponse> getPaidList(String email) {return orderService.getCompleteOrders(email);}

    @GetMapping("/get_invalid")
    public List<GetOrderResponse> getInvalidList(String email) {return orderService.getInvalidOrders(email);}

    @GetMapping("/get_order_detail")
    public OrderDetailResponse getOrderDetail(int oid) {return orderService.getOrderDetail(oid);}

    @GetMapping("/cancel_order")
    public void cancelOrder(int oid) {orderService.cancelOrder(oid);}

    @GetMapping("/pay_order")
    public boolean payOrder(int oid) { return orderService.payOrder(oid);}

    @GetMapping("/get_express_state")
    public String getExpressState(int oid) {return orderService.getExpressState(oid);}

    @GetMapping("/get_not_receive")
    public List<OrderExpressResponse> getNotReceiveList(String restId) {return  orderService.getNotReceiveOrders(restId);}

    @GetMapping("/get_not_deliver")
    public List<OrderExpressResponse> getNotDeliverList(String restId) {return  orderService.getNotDeliverOrders(restId);}

    @GetMapping("/get_delivered")
    public List<OrderExpressResponse> getDeliveredList(String restId) {return  orderService.getDeliveredOrders(restId);}

    /**
     * 商家接单
     * @param oid
     */
    @GetMapping("/receive_order")
    public void receiveOrder(int oid) {orderService.receiveOrder(oid);}

    /**
     * 商家发货
     * @param oid
     */
    @GetMapping("/deliver_order")
    public void deliverOrder(int oid) {orderService.deliverOrder(oid);}

    /**
     * 会员确认收货
     * @param oid
     */
    @GetMapping("/accept_order")
    public void acceptOrder(int oid) {orderService.acceptOrder(oid);}

    /**
     * 会员退订商品
     * @param oid
     */
    @GetMapping("/set_order_cancel")
    public void setOrderCancel(int oid) {orderService.setOrderCancel(oid);}

    /**
     * 商家接受退订
     * @param oid
     */
    @GetMapping("/agree_cancel")
    public void agreeCancel(int oid) {orderService.agreeCancel(oid);}

    @GetMapping("/get_member_statistics")
    public List<MemberStatisticsResponse> getMemberStatistics(String email) {return orderService.getMemberStatistics(email);}
}
