package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean save(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 删除用户
     * @param uuid
     * @return
     */
    public boolean delete(Integer uuid);

    /**
     * 查询单个用户信息
     * @param uuid
     * @return
     */
    public User get(Integer uuid);

    /**
     * 查询全部用户信息
     * @return
     */
    public List<User> getAll();


    /**
     * 根据用户名密码查询个人信息
     * @param userName 用户名
     * @param password 密码信息
     * @return
     */
    //注意：数据层操作不要和业务层操作的名称混淆，通常数据层仅反映与数据库间的信息交换，不体现业务逻辑
    public User getByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);
}
