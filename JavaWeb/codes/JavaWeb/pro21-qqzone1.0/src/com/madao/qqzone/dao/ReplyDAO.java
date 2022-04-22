package com.madao.qqzone.dao;

import com.madao.qqzone.pojo.Reply;
import com.madao.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyDAO {

    List<Reply> getRlyList(Topic topic);
    void addReply(Reply reply);
    void delReply(Integer id);
}
