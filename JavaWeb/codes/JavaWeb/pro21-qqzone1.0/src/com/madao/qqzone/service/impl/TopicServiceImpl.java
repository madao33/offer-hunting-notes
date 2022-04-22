package com.madao.qqzone.service.impl;

import com.madao.qqzone.dao.TopicDAO;
import com.madao.qqzone.pojo.Topic;
import com.madao.qqzone.pojo.UserBasic;
import com.madao.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
