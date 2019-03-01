package com.example.demo.dao.admin;

import com.example.demo.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/3/1
 */
public interface AdminRepository extends CrudRepository<Admin, String> {

    Optional<Admin> findByUsername(String name);
}
