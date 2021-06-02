package com.example.demo0921.dao;

import com.example.demo0921.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User queryUserByUserName(@Param("userName") String userName);
}
