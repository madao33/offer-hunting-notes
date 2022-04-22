package com.madao.qqzone.dao.impl;

import com.madao.myssm.basedao.BaseDAO;
import com.madao.qqzone.dao.TopicDAO;
import com.madao.qqzone.pojo.Topic;
import com.madao.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return null;
    }
}
