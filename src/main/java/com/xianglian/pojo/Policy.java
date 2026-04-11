package com.xianglian.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Policy {
    private Integer id;
    private String title;
    private String content;
    private String type;
    private String area;
    private String money;
    private String conditions;
    private String materials;
    private String process;
    private String contact;
    private Date createTime;
    private Date updateTime;
}