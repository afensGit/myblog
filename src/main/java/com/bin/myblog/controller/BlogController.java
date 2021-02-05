package com.bin.myblog.controller;

import com.bin.myblog.common.Page;
import com.bin.myblog.pojo.Blog;
import com.bin.myblog.pojo.Type;
import com.bin.myblog.pojo.User;
import com.bin.myblog.service.BlogService;
import com.bin.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String toBlogs(Blog blog,
                          @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "currentCount",required = false,defaultValue = "5") Integer currentCount,
                          Model model){
        Page<Blog> page = new Page<>(currentPage, currentCount);
        Map<String,Object> map = new HashMap<>();
        map.put("title",blog.getTitle());
        map.put("typeId",blog.getId());
        map.put("recommend",blog.getRecommend());
        map.put("currentCount",currentCount);
        map.put("startIndex",page.getStartIndex());
        List<Blog> blogs = blogService.listBlog(map);
        List<Type> types = typeService.getAllType();
        Map<Long,Type> typeMap = new HashMap<>();
        for (Type type : types) {
            typeMap.put(type.getId(),type);
        }
        for (Blog b : blogs) {
            b.setType(typeMap.get(b.getTypeId()));
        }
        System.out.println(blogs);
        page.setBeans(blogs);
        page.setTotalCount(blogService.countBlog());
        model.addAttribute("page",page);
        model.addAttribute("types",types);
        System.out.println(types);
        return "admin/blogs";
    }

    @PostMapping("/blogSearch")
    public String toBlogSearch(Blog blog,
//                          @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
//                          @RequestParam(value = "currentCount",required = false,defaultValue = "2") Integer currentCount,
                               Integer page,
                          Model model){
        Page<Blog> blogPage = new Page<>(page, 5);
        Map<String,Object> map = new HashMap<>();
        map.put("title",blog.getTitle());
        map.put("typeId",blog.getTypeId());
        map.put("recommend",blog.getRecommend());
        map.put("currentCount",blogPage.getCurrentCount());
        map.put("startIndex",blogPage.getStartIndex());
        List<Blog> blogs = blogService.listBlog(map);
        System.out.println(blogs);
        List<Type> types = typeService.getAllType();
        Map<Long,Type> typeMap = new HashMap<>();
        for (Type type : types) {
            typeMap.put(type.getId(),type);
        }
        for (Blog b : blogs) {
            b.setType(typeMap.get(b.getTypeId()));
        }
        blogPage.setBeans(blogs);
        blogPage.setTotalCount(blogService.countBlog());
        model.addAttribute("page",blogPage);
        model.addAttribute("types",types);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/toInput/{id}")
    public String toUpdateBlog(@PathVariable("id") Long id ,Model model){
        Blog blog = blogService.getBlogById(id);
        Type type = typeService.getTypeById(blog.getTypeId());
        blog.setType(type);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @GetMapping("/toInput")
    public String toInput(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
        System.out.println(blog);
        User user =(User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message","博客保存成功！！！");
        return "redirect:/blog/blogs";
    }

}
