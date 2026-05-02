package com.xianglian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> index() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "xianglian-web API");
        data.put("version", "1.0.0");
        data.put("docs", "/doc.html");
        return data;
    }
}