package com.example.demo.domain;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)  //指定启动类
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate);
        /*userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
        userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate));*/
        for (int i = 0; i < 100; i++) {
            userRepository.save(new User("a" + i, "xxxx" + i, "aa@126" + i + ".com", "aa" + i, formattedDate));
        }

        //Assert.assertEquals(2, userRepository.findAll().size());
        // Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        //userRepository.delete(userRepository.findByUserName("aa1"));
    }

    @Test
    public void test2() throws Exception {
        int page = 2, size = 10;
        Sort id = new Sort(Sort.Direction.ASC, "id");
        //模拟页码请求  它实现了Pageable 接口
        Pageable pageable = new PageRequest(page, size, id);
        Page<User> all = userRepository.findAll(pageable);
        Iterator<User> iterator = all.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        //限制查询  详情参见纯洁的微笑的博客
        User firstByOrderByUserNameAsc = userRepository.findFirstByOrderByUserNameAsc();
        System.out.println(firstByOrderByUserNameAsc);
    }

    @Test
    public void test4(){
        //详情见官方文档 56例
        List<User> user = userRepository.queryUserWhereIdGreaterThan(3l);
        System.out.println(user);
    }

    @Test
    public void test5(){
        List<User> byAndSort = userRepository.findByAndSort("a", new Sort("id"));
        System.out.println(byAndSort);
    }
    @Test
    public void test6(){
        //userRepository.updateUser("misaki","见崎鸣",6l);
        //源码实际为 return floor(参数+0.5)
        System.out.println(Math.round(-2.5));
        System.out.println(Math.round(2.5));
        System.out.println(Math.round(-2.50));
        System.out.println(Math.round(-3.5));
        System.out.println(Math.round(-3.50));
        System.out.println(Math.round(-2.51));
        System.out.println(Math.round(-2.43));
        System.out.println(Math.round(-3.56));
    }
}

