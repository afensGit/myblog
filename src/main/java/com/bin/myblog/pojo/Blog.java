package com.bin.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 博客实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;//正文
    private String firstPicture;
    private String flag;//标记
    private Integer views;//浏览次数
    private Boolean appreciation;//赞赏
    private Boolean shareStatement;//版权声明
    private Boolean comment;//评论是否开启
    private Boolean published;//是否公开
    private Boolean recommend;//是否推荐
    private String description;//博客描述

    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    private Long userId;
    private Long typeId;

    private Type type;

}
