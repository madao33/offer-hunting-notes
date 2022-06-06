package com.itheima._16动态代理;

/**
  业务接口
 */
public interface UserService {
    String login(String loginName, String passWord);
    void deleteAll();
    void updateAll();
}
