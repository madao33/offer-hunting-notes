package com.madao.qqzone.dao;

import com.madao.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicDAO {

    public UserBasic getUserBasic(String loginId, String pwd);

    public List<UserBasic> getUserBasicList(UserBasic userBasic);

    UserBasic getUserBasicById(Integer id);

}
