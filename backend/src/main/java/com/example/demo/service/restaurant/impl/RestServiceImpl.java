package com.example.demo.service.restaurant.impl;

import com.example.demo.dao.restaurant.FoodRepository;
import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import com.example.demo.payloads.restaurant.FindRestByDistResponse;
import com.example.demo.payloads.restaurant.FoodListResponse;
import com.example.demo.payloads.restaurant.NewFoodRequest;
import com.example.demo.service.restaurant.RestService;
import com.example.demo.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/13
 */

@Service
public class RestServiceImpl implements RestService {

    private RestRepository restRepository;
    private FoodRepository foodRepository;

    @Autowired
    public RestServiceImpl(RestRepository restRepository,
                           FoodRepository foodRepository){
        this.restRepository = restRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public String register(String restName, String district,  String address, String type) {

        String id = getID();
        Restaurant restaurant = new Restaurant(id, restName, district, address, type, false);
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

        Restaurant rest = new Restaurant();
        rest.setId(id);

        List<Food> foods = foodRepository.findByRest(rest);

        ArrayList<FoodListResponse> foodList = new ArrayList<>();

        if(foods != null) {
            for(Food f: foods) {
                FoodListResponse response = new FoodListResponse(f.getName(), f.getType(), f.getPrice(), f.getAmount(), f.getImage());
                foodList.add(response);
            }
        }

        return foodList;
    }
}
