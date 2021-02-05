package com.bin.myblog.dao;

import com.bin.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<Comment> getAllComment();

    List<Comment> getCommentByBlog(Long blogId);

    Integer saveComment(Comment comment);

    Integer deleteComment(Long id);


}
