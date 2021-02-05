package com.bin.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private User user;
    private String content;
    private Date createTime;
    private Long userId;
    private Long blogId;
    private Long contentId;
    private String parentNickname;
    private List<Comment> childComment = new ArrayList<>();
}
