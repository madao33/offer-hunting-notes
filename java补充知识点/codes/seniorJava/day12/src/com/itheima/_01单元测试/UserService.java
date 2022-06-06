package com.itheima._01单元测试;

/**
 * 完成用户数据的增删改查操作登录等。
 */
public class UserService {
    public String login(String loginName , String passWord){
        if("admin".equals(loginName)&&"123456".equals(passWord)){
            return "success";
        }
        return "用户名或者密码错误！";
    }

    public void chu(int a , int b){
        int c = a / b ;
        System.out.println(c);
    }
}
