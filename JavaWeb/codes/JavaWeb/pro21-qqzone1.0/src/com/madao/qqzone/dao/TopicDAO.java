package com.madao.qqzone.dao;

import com.madao.qqzone.pojo.Topic;
import com.madao.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {

    public List<Topic> getTopicList(UserBasic userBasic);

    public void addTopic(Topic topic);

    void delTopic(Topic topic);

    Topic getTopic(Integer id);
}
