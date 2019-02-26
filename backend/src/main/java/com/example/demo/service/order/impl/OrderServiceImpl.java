package com.example.demo.service.order.impl;

import com.example.demo.dao.member.MemberRepository;
import com.example.demo.dao.order.OrderInfoRepository;
import com.example.demo.dao.order.OrderRepository;
import com.example.demo.dao.restaurant.FoodRepository;
import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.*;
import com.example.demo.payloads.order.NewOrderRequest;
import com.example.demo.payloads.order.GetOrderResponse;
import com.example.demo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/26
 */
@Service
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private RestRepository restRepository;
    private FoodRepository foodRepository;
    private OrderRepository orderRepository;
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            RestRepository restRepository,
                            FoodRepository foodRepository,
                            OrderRepository orderRepository,
                            OrderInfoRepository orderInfoRepository) {
        this.memberRepository = memberRepository;
        this.restRepository = restRepository;
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    public void addNewOrder(NewOrderRequest request) {
        String email = request.getEmail();
        String restId = request.getRestId();
        double sum = request.getSum();
        LocalDateTime orderDate = LocalDateTime.now();

        Member member = memberRepository.findByEmail(email).get();
        Restaurant restaurant = restRepository.findById(restId).get();

        Orders orders = new Orders(member, restaurant, sum, true, false, orderDate);
        Orders newOrder = orderRepository.save(orders);

        List<FoodInfo> foodList = request.getFoodList();

        for(FoodInfo f: foodList) {

            Food food = foodRepository.findById(f.getId()).get();
            int num = f.getNum();

            OrderInfo orderInfo = new OrderInfo(newOrder, food, num);
            orderInfoRepository.save(orderInfo);
        }

        // 倒计时3分钟
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {
                if(!newOrder.isPaid()) {
                    newOrder.setValid(false);
                    orderRepository.save(newOrder);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 120000L, 120000L);

    }

    @Override
    public List<GetOrderResponse> getNotPaidOrders(String email) {

        List<Orders> orders = orderRepository.getNotPaidList(email);
        return getList(orders);
    }

    @Override
    public List<GetOrderResponse> getCompleteOrders(String email) {
        List<Orders> orders = orderRepository.getCompleteList(email);
        return getList(orders);
    }

    @Override
    public List<GetOrderResponse> getInvalidOrders(String email) {

        List<Orders> orders = orderRepository.getInvalidList(email);
        return getList(orders);
    }

    private ArrayList<GetOrderResponse> getList(List<Orders> orders) {
        ArrayList<GetOrderResponse> orderList = new ArrayList<>();
        if(orders != null) {
            for(Orders o: orders) {
                int id = o.getId();
                double sum = o.getSum();
                LocalDateTime orderTime = o.getOrderTime();
                GetOrderResponse response = new GetOrderResponse(id, orderTime, sum);
                orderList.add(response);
            }
        }
        return orderList;
    }
}
