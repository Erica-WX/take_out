package com.example.demo.dao.member;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/3
 */
public interface MemberRepository extends CrudRepository<Member, String> {
    Optional<Member> findByEmail(String email);

    @Query(value = "select * from member", nativeQuery = true)
    List<Member> getAll();
}
