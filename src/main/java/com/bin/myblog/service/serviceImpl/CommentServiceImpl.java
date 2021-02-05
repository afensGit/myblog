package com.bin.myblog.service.serviceImpl;

import com.bin.myblog.dao.CommentMapper;
import com.bin.myblog.pojo.Comment;
import com.bin.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public List<Comment> getAllComment() {
        return commentMapper.getAllComment();
    }

    @Transactional
    @Override
    public List<Comment> getCommentByBlog(Long blogId) {
        return commentMapper.getCommentByBlog(blogId);
    }

    @Transactional
    @Override
    public Integer saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }

    @Transactional
    @Override
    public Integer deleteComment(Long id) {
        return commentMapper.deleteComment(id);
    }
}
