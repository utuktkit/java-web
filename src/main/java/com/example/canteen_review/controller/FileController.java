package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.utils.OSSUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) throws Exception {

        String url = OSSUtil.upload(file);
        return Result.success(url);
    }
}
