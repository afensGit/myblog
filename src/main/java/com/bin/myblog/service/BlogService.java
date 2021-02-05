package com.bin.myblog.service;

import com.bin.myblog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Integer saveBlog(Blog blog);

    Blog getBlogById(Long id);

    List<Blog> listBlog(Map<String,Object> map);

    Integer updateBlog(Blog blog);

    Integer deleteBlog(Long id);

    List<Blog> getBlogByPublished(Map<String,Object> map);

    List<Blog> getHotBlog();

    List<Blog> getPublishedBlog();

    Integer countBlog();

    List<Blog> getBlogByTypeId(Map<String, Object> map);
}
