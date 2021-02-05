package com.bin.myblog.service;

import com.bin.myblog.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment();

    List<Comment> getCommentByBlog(Long blogId);

    Integer saveComment(Comment comment);

    Integer deleteComment(Long id);
}
