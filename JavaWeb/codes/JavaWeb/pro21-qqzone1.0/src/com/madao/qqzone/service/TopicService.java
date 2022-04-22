package com.madao.qqzone.service;

import com.madao.qqzone.pojo.Topic;
import com.madao.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);
}
