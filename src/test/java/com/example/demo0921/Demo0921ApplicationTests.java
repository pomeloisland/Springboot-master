package com.example.demo0921;

import com.example.demo0921.pojo.User;
import com.example.demo0921.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo0921ApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User user = userService.queryUserByUserName("李三");
        System.out.println(user);
    }

}
