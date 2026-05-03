package com.xianglian.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Favorite {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String targetType;
    private Integer targetId;
    private Date createTime;
}