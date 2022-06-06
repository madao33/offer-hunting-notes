package com.itheima._16动态代理;

/**
 业务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public String login(String loginName, String passWord) {
        String flag = "登陆名称或者密码错误";
        if("admin".equals(loginName) && "123456".equals(passWord)){
            flag = "success";
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void deleteAll() {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("删除成功！");
    }

    @Override
    public void updateAll() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("更新成功！");
    }
}
