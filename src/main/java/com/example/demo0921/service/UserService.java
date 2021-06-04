package com.example.demo0921.service;

import com.example.demo0921.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User queryUserByUserName(@Param("userName") String userName);

    int addUser(User user);
}
