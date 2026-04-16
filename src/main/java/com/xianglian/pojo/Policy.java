package com.xianglian.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}