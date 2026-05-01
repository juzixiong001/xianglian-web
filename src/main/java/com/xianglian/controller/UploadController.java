package com.xianglian.controller;

import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "上传模块", description = "文件上传接口")
public class UploadController {

    private static final String UPLOAD_DIR = "D:/upload";

    @Operation(summary = "上传图片")
    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(400, "文件不能为空");
        }

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + suffix;

        File dest = new File(uploadDir, filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(500, "文件上传失败");
        }

        String fileUrl = "/upload/" + filename;
        return Result.success(fileUrl);
    }
}