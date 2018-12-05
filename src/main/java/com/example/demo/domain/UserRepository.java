package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
    Page<User> findByUserName(String userName,Pageable pageable);
    User findFirstByOrderByUserNameAsc();
    //注意 ORM
    @Query(value = "select u from User u where id > ?1")
    List<User> queryUserWhereIdGreaterThan(Long id);

    //默认升序
    @Query("select u from User u where u.userName like ?1%")
    List<User> findByAndSort(String name, Sort sort);
    @Modifying
    @Transactional  //必须添加事物 不然会抛出异常（修改）
    @Query("update User u set u.userName = :newname,u.nickName = :newnickname where u.id = :id")
    void updateUser(@Param("newname")String name,@Param("newnickname")String nickname,@Param("id") Long id);
}
