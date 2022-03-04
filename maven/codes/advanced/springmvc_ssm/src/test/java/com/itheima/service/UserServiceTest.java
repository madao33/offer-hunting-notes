package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("Jock");
        user.setPassword("root");
        user.setRealName("Jockme");
        user.setGender(1);
        user.setBirthday(new Date(333333000000L));

        userService.save(user);
    }

    @Test
    public void testDelete(){
        User user = new User();
        userService.delete(3);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUuid(1);
        user.setUserName("Jockme");
        user.setPassword("root");
        user.setRealName("JockIsMe");
        user.setGender(1);
        user.setBirthday(new Date(333333000000L));

        userService.update(user);
    }

    @Test
    public void testGet(){
        User user = userService.get(1);
        System.out.println(user);
    }

    @Test
    public void testGetAll(){
        PageInfo<User> all = userService.getAll(2, 2);
        System.out.println(all);
        System.out.println(all.getList().get(0));
        System.out.println(all.getList().get(1));
    }

    @Test
    public void testLogin(){
        User user = userService.login("Jockme", "root");
        System.out.println(user);
    }
}
