package com.example.demo.dao;

import com.example.demo.dao.restaurant.FoodRepository;
import com.example.demo.dao.restaurant.RestRepository;
import com.example.demo.entity.Food;
import com.example.demo.entity.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/17
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class FoodTest {

    private FoodRepository restRepository;

    @Autowired
    public void setRestRepository(FoodRepository restRepository){
        this.restRepository = restRepository;
    }

    @Test
    public void test(){
        String restId = "6293602";
        Restaurant r = new Restaurant();
        r.setId(restId);

        Food food = new Food(r, "name", "type", 0, 0, null, null ,"");
        restRepository.save(food);
    }
}
