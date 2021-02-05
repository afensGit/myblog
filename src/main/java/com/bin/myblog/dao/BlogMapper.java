package com.bin.myblog.dao;

import com.bin.myblog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper {

    Integer saveBlog(Blog blog);

    Blog getBlogById(Long id);

    List<Blog> listBlog(Map<String,Object> map);

    Integer updateBlog(Blog blog);

    Integer deleteBlog(Long id);

    Integer countBlog();

    List<Blog> getBlogByPublished(Map<String,Object> map);

    List<Blog> getHotBlog();

    List<Blog> getPublishedBlog();

    List<Blog> getBlogByTypeId(Map<String, Object> map);
}
