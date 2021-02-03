package com.personblog.service;

import com.personblog.entity.Message;

import java.util.List;

/**
 * @Description: 留言业务层接口
 * @URL:
 */
public interface MessageService {

    //查询留言列表
    List<Message> listMessage();

    //保存留言
    int saveMessage(Message message);

    //删除留言
    void deleteMessage(Long id);

}