package com.bin.myblog.service;

import com.bin.myblog.pojo.Message;

import java.util.List;

public interface MessageService {

    Message getMessageById(Long id);

    List<Message> getAllMessage();

    Integer saveMessage(Message message);

    Integer deleteMessage(Long id);

}
