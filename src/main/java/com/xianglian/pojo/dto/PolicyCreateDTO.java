package com.xianglian.pojo.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class PolicyCreateDTO {
    private String title;
    private String content;
    private String type;
    private String province;
    private String city;
    private String areaDetail;
    private String money;
    private String conditions;
    private String materials;
    private String process;
    private String contact;

    public String buildArea() {
        return Arrays.asList(province, city, areaDetail)
                .stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.joining(" "));
    }
}