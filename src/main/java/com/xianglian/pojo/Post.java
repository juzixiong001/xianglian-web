package com.xianglian.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private String type;
    private Date createTime;
    private Date updateTime;
}