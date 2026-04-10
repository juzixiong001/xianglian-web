package com.xianglian.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Policy {
    private Integer id;
    private String name;
    private String content;
    private Date createTime;
    private Date updateTime;
}

