package com.example.demo.service.restaurant.impl;

import com.example.demo.dao.restaurant.*;
import com.example.demo.entity.*;
import com.example.demo.payloads.restaurant.*;
import com.example.demo.service.restaurant.RestService;
import com.example.demo.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Service
public class RestServiceImpl implements RestService {

    private RestRepository restRepository;
    private FoodRepository foodRepository;
    private SetMealRepository setMealRepository;
    private SetMealInfoRepository setMealInfoRepository;
    private DiscountRepository discountRepository;
    private ModifyInfoRepository modifyInfoRepository;

    @Autowired
    public RestServiceImpl(RestRepository restRepository,
                           FoodRepository foodRepository,
                           SetMealRepository setMealRepository,
                           SetMealInfoRepository setMealInfoRepository,
                           DiscountRepository discountRepository,
                           ModifyInfoRepository modifyInfoRepository){
        this.restRepository = restRepository;
        this.foodRepository = foodRepository;
        this.setMealRepository = setMealRepository;
        this.setMealInfoRepository = setMealInfoRepository;
        this.discountRepository = discountRepository;
        this.modifyInfoRepository = modifyInfoRepository;
    }

    @Override
    public String register(String restName, String district,  String address, String type) {

        String id = getID();
        Restaurant restaurant = new Restaurant(id, restName, district, address, type, true);
        restRepository.save(restaurant);
        return id;
    }

    @Override
    public String getID() {
        String randomCode = RandomCodeUtil.generateUniqueCode();
        String id = randomCode.substring(0,7);
        return id;
    }

    @Override
    public Restaurant login(String id) {
        Optional<Restaurant> optional = restRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void addNewFood(NewFoodRequest foodRequest) {
        Restaurant rest = new Restaurant();
        rest.setId(foodRequest.getRestId());
        Food food = new Food(rest,
                            foodRequest.getName(),
                            foodRequest.getType(),
                            foodRequest.getPrice(),
                            foodRequest.getAmount(),
                            foodRequest.getStartDate(),
                            foodRequest.getEndDate(),
                            foodRequest.getImage());

        foodRepository.save(food);
    }

    @Override
    public List<FindRestByDistResponse> getRestByDistric(String district) {

        List<Restaurant> restList = restRepository.findByDistrict(district);

        ArrayList<FindRestByDistResponse> rests = new ArrayList<>();

        if(restList != null) {
            for(Restaurant r: restList) {

                if(r.isUsable()){
                    FindRestByDistResponse restResponse = new FindRestByDistResponse();
                    restResponse.setId(r.getId());
                    restResponse.setAddress(r.getAddress());
                    restResponse.setName(r.getName());
                    restResponse.setType(r.getType());

                    rests.add(restResponse);
                }
            }
        }

        return rests;
    }

    @Override
    public List<FoodListResponse> getFoodList(String id) {

        LocalDate now = LocalDate.now();

        List<Food> foods = foodRepository.getFoodByDate(id, now);

        ArrayList<FoodListResponse> foodList = new ArrayList<>();

        if(foods != null) {
            for(Food f: foods) {
                FoodListResponse response = new FoodListResponse(f.getId(), f.getName(), f.getType(), f.getPrice(), f.getAmount(), f.getImage());
                foodList.add(response);
            }
        }

        return foodList;
    }

    @Override
    public void setNewSetMeal(NewSetMealRequest setMealRequest) {

        String restId = setMealRequest.getRestId();
        String s_name = setMealRequest.getName();
        double s_price = setMealRequest.getPrice();
        int amount = setMealRequest.getAmount();
        LocalDate startDate = setMealRequest.getStartDate();
        LocalDate endDate = setMealRequest.getEndDate();
        String image = setMealRequest.getImage();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restId);

        SetMeal setMeal = new SetMeal(s_name, restaurant, s_price, amount, startDate, endDate, image);
        SetMeal newSetMeal = setMealRepository.save(setMeal);

        Food f = new Food(restaurant, s_name, "套餐", s_price, amount, startDate, endDate, image);
        foodRepository.save(f);


        List<FoodInfoInSetMeal> foodList = setMealRequest.getFoodList();
        for(FoodInfoInSetMeal foodInfo: foodList) {
            int f_id = foodInfo.getId();
            int num = foodInfo.getNum();

            Food food = new Food();
            food.setId(f_id);

            SetMealInfo setMealInfo = new SetMealInfo(newSetMeal, food, num);
            setMealInfoRepository.save(setMealInfo);
        }
    }

    @Override
    public void setDiscount(DiscountInfoResponse discountInfo) {

        LocalDate startDate = discountInfo.getStartDate();
        LocalDate endDate = discountInfo.getEndDate();
        double fullMoney = discountInfo.getFullMoney();
        double disMoney = discountInfo.getDisMoney();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(discountInfo.getRestId());

        Discount discount = new Discount(restaurant, fullMoney, disMoney ,startDate ,endDate);
        discountRepository.save(discount);
    }

    @Override
    public List<DiscountInfoResponse> getDiscountList(String restId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restId);
        List<Discount> discounts = discountRepository.findByRestaurant(restaurant);

        ArrayList<DiscountInfoResponse> disInfoList = new ArrayList<>();

        if(discounts != null) {
            for(Discount d: discounts) {
                DiscountInfoResponse response = new DiscountInfoResponse(d.getFullMoney(), d.getDisMoney(), d.getStartDate(), d.getEndDate());
                disInfoList.add(response);

            }
        }

        return disInfoList;
    }

    @Override
    public void saveInfo(EditRestInfoResquest resquest) {
        String id = resquest.getId();
        String name = resquest.getName();
        LocalDateTime requestTime = LocalDateTime.now();
        String district = resquest.getDistrict();
        String address = resquest.getAddress();
        String type = resquest.getType();

        Restaurant restaurant = restRepository.findById(id).get();

        ModifyInfo modifyInfo = new ModifyInfo(restaurant, name, requestTime, district, address, type);
        modifyInfoRepository.save(modifyInfo);

    }

    @Override
    public OrderResponse calOrder(CalOrderRequest request) {

        int level = request.getLevel();
        double sum = request.getSum();

        //计算会员等级折扣
        double disOfLevel = getDisBylevel(level);
        double disMoneyOfLevel = sum * (1 - disOfLevel);

        disMoneyOfLevel = (double)Math.round(disMoneyOfLevel * 100) / 100;

        sum -= disMoneyOfLevel;

        //获取当前店铺的优惠
        String restId = request.getRestId();
        List<Discount> discountList = discountRepository.getValidDiscount(restId, LocalDate.now());
        ArrayList<DiscountInfo> discountInfos = new ArrayList<>();

        for(Discount d: discountList) {
           DiscountInfo discountInfo = new DiscountInfo();
           discountInfo.setFullMoney(d.getFullMoney());
           discountInfo.setDisMoney(d.getDisMoney());

           discountInfos.add(discountInfo);
        }

        //对优惠进行降序排序
        Collections.sort(discountInfos, new Comparator<DiscountInfo>() {
            @Override
            public int compare(DiscountInfo o1, DiscountInfo o2) {
                return (int)(o2.getDisMoney() - o1.getDisMoney());
            }
        });

        /*for(DiscountInfo d: discountInfos) {
            System.out.println("满" + d.getFullMoney() + "减" + d.getDisMoney());
        }*/

        //计算满足该店铺的最大优惠
        double fullMoney = 0;
        double disMoneyByRest = 0;
        for(DiscountInfo d: discountInfos) {
            double full = d.getFullMoney();

            if(sum < full) {
                continue;
            } else {
                fullMoney = full;
                disMoneyByRest = d.getDisMoney();
                break;
            }
        }

        sum -= disMoneyByRest;

        sum = (double)Math.round(sum * 100) /100;

        OrderResponse response = new OrderResponse(restId, sum, fullMoney, disMoneyByRest, disMoneyOfLevel);

        return response;
    }

    @Override
    public boolean isApproved(String restId) {

        Restaurant restaurant = restRepository.findById(restId).get();
        List<ModifyInfo> modifyInfoList = modifyInfoRepository.findByRestaurant(restaurant);

        if(modifyInfoList == null || modifyInfoList.size() == 0) {
            return true;
        }else {
            return false;
        }
    }


    private double getDisBylevel(int level) {

        double discount = 0;

        switch (level){
            case 1:
                discount = 0.95;
                break;

            case 2:
                discount = 0.9;
                break;

            case 3:
                discount = 0.88;
                break;

            case 4:
                discount = 0.85;
                break;

            case 5:
                discount = 0.8;
                break;
        }

        return discount;
    }
}
