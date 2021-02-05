package com.bin.myblog.service.serviceImpl;

import com.bin.myblog.dao.BlogMapper;
import com.bin.myblog.pojo.Blog;
import com.bin.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Override
    public Integer saveBlog(Blog blog) {
        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            return blogMapper.saveBlog(blog);
        }else {
            blog.setUpdateTime(new Date());
            return blogMapper.updateBlog(blog);
        }

    }

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Transactional
    @Override
    public List<Blog> listBlog(Map<String, Object> map) {
        return blogMapper.listBlog(map);
    }

    @Transactional
    @Override
    public Integer updateBlog(Blog blog) {
        return null;
    }

    @Transactional
    @Override
    public Integer deleteBlog(Long id) {
        return null;
    }

    @Override
    public List<Blog> getBlogByPublished(Map<String,Object> map) {
        return blogMapper.getBlogByPublished(map);
    }

    @Override
    public List<Blog> getHotBlog() {
        return blogMapper.getHotBlog();
    }

    @Override
    public List<Blog> getPublishedBlog() {
        return blogMapper.getPublishedBlog();
    }

    @Override
    public Integer countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public List<Blog> getBlogByTypeId(Map<String, Object> map) {
        return blogMapper.getBlogByTypeId(map);
    }
}
