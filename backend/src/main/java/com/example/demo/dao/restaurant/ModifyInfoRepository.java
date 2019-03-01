package com.example.demo.dao.restaurant;

import com.example.demo.entity.ModifyInfo;
import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public interface ModifyInfoRepository extends CrudRepository<ModifyInfo, Integer> {

    @Query(value = "select * from modify_info", nativeQuery = true)
    List<ModifyInfo> getAll();

}
