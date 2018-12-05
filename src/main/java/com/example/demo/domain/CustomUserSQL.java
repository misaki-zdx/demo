package com.example.demo.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * spring data 方式自定义sql语句
 */


public interface CustomUserSQL {

//在SQL的查询方法上面使用@Query注解
//如涉及到删除和修改在需要加上@Modifying.
//也可以根据需要添加 @Transactional 对事物的支持,查询超时的设置等

    @Query("select u from user u where id > ?1")
    List<User> queryUserWhereIdGreaterThan(int id);

}
