package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Integer uuid) {
        return userDao.delete(uuid);
    }

    @Override
    public User get(Integer uuid) {
        return userDao.get(uuid);
    }

    @Override
    public PageInfo<User> getAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<User> all = userDao.getAll();
        return new PageInfo<User>(all);
    }

    @Override
    public User login(String userName, String password) {
        return userDao.getByUserNameAndPassword(userName,password);
    }
}
