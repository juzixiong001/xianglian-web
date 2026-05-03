package com.xianglian.pojo.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class PostCreateDTO {
    private String title;
    private String content;
    private String type;
    private String province;
    private String city;
    private String areaDetail;
    private String price;
    private String contact;
    private String images;

    public String buildArea() {
        return Arrays.asList(province, city, areaDetail)
                .stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.joining(" "));
    }
}