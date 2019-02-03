package com.example.demo.dao.user;

import com.example.demo.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */
public interface MemberRepository extends CrudRepository<Member, String> {
    Optional<Member> findByEmail(String email);
}
