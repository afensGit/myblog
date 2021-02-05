package com.bin.myblog.controller;

import com.bin.myblog.common.Page;
import com.bin.myblog.exception.NotFoundException;
import com.bin.myblog.pojo.*;
import com.bin.myblog.service.*;
import com.bin.myblog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class RouterController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @GetMapping({"/","/index"})
    public String toIndex(){
        return "index";
    }

    @GetMapping("/article")
    public String toArticle(String queryText,
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "currentCount",required = false,defaultValue = "5") Integer currentCount,
            Model model){
        Map<String,Object> map = new HashMap<>();
        Page<Blog> page = new Page<>(currentPage, currentCount);
        map.put("queryText",queryText);
        map.put("published",true);
        map.put("startIndex",page.getStartIndex());
        map.put("currentCount",page.getCurrentCount());
        List<Blog> blogs = blogService.getBlogByPublished(map);
        List<Type> types = typeService.getAllType();
        Map<Long,Type> typeMap = new HashMap<>();
        for (Type type : types) {
            typeMap.put(type.getId(),type);
        }
        for (Blog b : blogs) {
            b.setType(typeMap.get(b.getTypeId()));
        }
        page.setBeans(blogs);
        page.setTotalCount(blogService.countBlog());
        model.addAttribute("page",page);
        model.addAttribute("types",types);
        model.addAttribute("hotBlogs",blogService.getHotBlog());
        model.addAttribute("publishedBlogs",blogService.getPublishedBlog());
        return "article";
    }

    @GetMapping("/article/{id}")
    public String toTypeArticle(@PathVariable("id") Long id,
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "currentCount",required = false,defaultValue = "5") Integer currentCount,
            Model model){
        Map<String,Object> map = new HashMap<>();
        Page<Blog> page = new Page<>(currentPage, currentCount);
        map.put("id",id);
        map.put("startIndex",page.getStartIndex());
        map.put("currentCount",page.getCurrentCount());
        List<Blog> blogs = blogService.getBlogByTypeId(map);
        List<Type> types = typeService.getAllType();
        Map<Long,Type> typeMap = new HashMap<>();
        for (Type type : types) {
            typeMap.put(type.getId(),type);
        }
        for (Blog b : blogs) {
            b.setType(typeMap.get(b.getTypeId()));
        }
        page.setBeans(blogs);
        page.setTotalCount(blogService.countBlog());
        model.addAttribute("page",page);
        model.addAttribute("types",types);
        model.addAttribute("hotBlogs",blogService.getHotBlog());
        model.addAttribute("publishedBlogs",blogService.getPublishedBlog());
        return "article";
    }

    @GetMapping("/toRead/{id}")
    public String toRead(@PathVariable("id") Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        if (blog == null){
            throw new NotFoundException("资源不存在！！！");
        }
        List<Comment> comments = commentService.getCommentByBlog(blog.getId());
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("comments",getBlogComment(comments));
        model.addAttribute("blog",blog);
        model.addAttribute("userBlog",userService.getUserById(blog.getUserId()));
        return "read";
    }

    public List<Comment> getBlogComment(List<Comment> comments){
        List<Comment> commentList = new ArrayList<>();
        Map<Long ,Comment> commentMap = new HashMap<>();
        for (Comment comment : comments) {
            User user = userService.getUserById(comment.getUserId());
            comment.setUser(user);
            commentMap.put(comment.getId(),comment);
        }
        for (Comment comment : comments) {
            if (comment.getContentId()==0){
                commentList.add(comment);
            }else {
                Comment parent = commentMap.get(comment.getContentId());
                parent.getChildComment().add(comment);
            }
        }
        System.out.println(commentList);
        return commentList;
    }

    @PostMapping("/remark")
    public String remark(Comment comment,HttpSession session,RedirectAttributes attributes){
        System.out.println(comment);
        User user =(User) session.getAttribute("user");
        if (user == null){
            attributes.addFlashAttribute("message","请先登录！！！");
        } else {
            comment.setCreateTime(new Date());
            comment.setUserId(user.getId());
            comment.setContentId(new Long(0));
            commentService.saveComment(comment);
            attributes.addFlashAttribute("message","评论成功！！！");
        }
        return "redirect:/toRead/"+comment.getBlogId();
    }

    @GetMapping("/comment/{blogId}")
    public String comments(@PathVariable("blogId") Long blogId,Model model){
        Blog blog = blogService.getBlogById(blogId);
        if (blog == null){
            throw new NotFoundException("资源不存在！！！");
        }
        List<Comment> comments = commentService.getCommentByBlog(blog.getId());
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("comments",getBlogComment(comments));
        model.addAttribute("blog",blog);
        model.addAttribute("userBlog",userService.getUserById(blog.getUserId()));
        return "read :: remarkList";
    }

    @PostMapping("/reply")
    public String reply(Comment comment,RedirectAttributes attributes,HttpSession session){
        System.out.println(comment);
        User user =(User) session.getAttribute("user");
        if (user == null){
            attributes.addFlashAttribute("message","请先登录！！！");
        } else {
            comment.setCreateTime(new Date());
            comment.setUserId(user.getId());
            commentService.saveComment(comment);
            attributes.addFlashAttribute("message","评论回复成功！！！");
        }
        return "redirect:/toRead/"+comment.getBlogId();
    }
    @GetMapping("/message")
    public String toMessage(Model model){
        List<Message> messages = messageService.getAllMessage();
        model.addAttribute("messages",getMessage(messages));
        return "message";
    }

    public List<Message> getMessage(List<Message> messageList){
        List<Message> messages = new ArrayList<>();
        Map<Long ,Message> messageMap = new HashMap<>();
        for (Message message : messageList) {
            User user = userService.getUserById(message.getUserId());
            message.setUser(user);
            messageMap.put(message.getId(),message);
        }
        for (Message message : messageList) {
            if (message.getMessageId()==0){
                messages.add(message);
            }else {
                Message parent = messageMap.get(message.getMessageId());
                parent.getChildMessage().add(message);
            }
        }
        System.out.println(messages);
        return messages;
    }

    @PostMapping("/saveMessage")
    public String saveMessage(Message message,HttpSession session,RedirectAttributes attributes){
        System.out.println(message);
        User user =(User) session.getAttribute("user");
        if (user==null){
            attributes.addFlashAttribute("message","请先登录！！！");
        }else if ("".equals(message.getContent())){
            attributes.addFlashAttribute("message","请输入评论内容！！！");
        }else {
            message.setCreateTime(new Date());
            message.setUserId(user.getId());
            messageService.saveMessage(message);
            attributes.addFlashAttribute("message","留言成功！！！");
        }
        return "redirect:/message";
    }

    @GetMapping("/about")
    public String toAbout(){
        return "about";
    }

    @GetMapping("/diary")
    public String toDiary(){
        return "diary";
    }

    @GetMapping("/link")
    public String toLink(){
        return "link";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "admin/login";
    }



    @ResponseBody
    @GetMapping("/test")
    public String test(@RequestParam(value = "currentPage" ,required = false ,defaultValue = "1") Integer currentPage
            ,@RequestParam(value = "currentCount" ,required = false ,defaultValue = "5") Integer currentCount){
        System.out.println(currentCount);
        System.out.println(currentPage);
        return "test";
    }
}
