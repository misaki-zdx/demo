package com.example.demo.domain;

import com.example.demo.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JdbcTemplateSQLTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    /**
     * 这样也行。。必须加''不然会当做字段处理; 不依赖于orm 原生sql
     */
    @Test
    public void selectList(){
        jdbcTemplate2.update("update user set sex = '女' where uid = 23");
    }

    @Test
    public void  test1(){

        //System.out.println(jdbcTemplate1 == jdbcTemplate2);  // >> true 任然単例
        //查阅了一下资料 单行的时候用values 比较快 多行的时候也是values  emmmmm....;但再sqlserver中只有values 记住

        jdbcTemplate1.update("insert into user values('007786','xxx@gamail.com','jack','123','2018-12-5','JJ') ");
    }


}
