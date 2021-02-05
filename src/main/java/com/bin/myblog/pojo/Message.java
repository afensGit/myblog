package com.bin.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Long id;
    private String content;
    private Date createTime;
    private Long userId;
    private Long messageId;

    private User user;
    private String parentNickname;
    private List<Message> childMessage = new ArrayList<>();
}
