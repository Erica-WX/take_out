package com.example.demo.controller;

import com.example.demo.payloads.order.NewOrderRequest;
import com.example.demo.payloads.order.GetOrderResponse;
import com.example.demo.payloads.order.OrderDetailResponse;
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
    public void payOrder(int oid) {orderService.payOrder(oid);}

    @GetMapping("/get_express_state")
    public String getExpressState(int oid) {return orderService.getExpressState(oid);}
}
