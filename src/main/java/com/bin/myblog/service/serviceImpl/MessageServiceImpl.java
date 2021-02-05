package com.bin.myblog.service.serviceImpl;

import com.bin.myblog.dao.MessageMapper;
import com.bin.myblog.pojo.Message;
import com.bin.myblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message getMessageById(Long id) {
        return messageMapper.getMessageById(id);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageMapper.getAllMessage();
    }

    @Override
    public Integer saveMessage(Message message) {
        return messageMapper.saveMessage(message);
    }

    @Override
    public Integer deleteMessage(Long id) {
        return messageMapper.deleteMessage(id);
    }
}
