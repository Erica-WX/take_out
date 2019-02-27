package com.example.demo.dao.order;

import com.example.demo.entity.ExpressState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/27
 */
public interface ExpressStateRepository extends CrudRepository<ExpressState, Integer> {

    Optional<ExpressState> findByOid(int oid);
}
