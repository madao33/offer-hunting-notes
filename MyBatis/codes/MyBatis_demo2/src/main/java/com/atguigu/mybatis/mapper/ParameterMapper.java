package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {


    User checkLoginByParam(@Param("username") String username, @Param("password") String password);

    int insertUser(User user);

    User checkLoginByMap(Map<String, Object> map);

    User checkLogin(String username, String password);

    List<User> getAllUser();

    User getUserByUsername(String username);




}
