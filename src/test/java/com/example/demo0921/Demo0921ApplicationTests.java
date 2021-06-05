package com.example.demo0921;

import com.example.demo0921.pojo.User;
import com.example.demo0921.service.UserServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo0921ApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //盐值
        String salt = "lisi";
        //原密码
        String pwd = "123456";
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
        System.out.println(result);

    }

}
