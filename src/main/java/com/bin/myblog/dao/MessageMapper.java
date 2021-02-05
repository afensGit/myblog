package com.bin.myblog.dao;

import com.bin.myblog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    Message getMessageById(Long id);

    List<Message> getAllMessage();

    Integer saveMessage(Message message);

    Integer deleteMessage(Long id);
}
