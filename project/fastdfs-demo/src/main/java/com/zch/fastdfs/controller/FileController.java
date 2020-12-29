package com.zch.fastdfs.controller;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zch
 * @date 2020/12/29 17:44
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping()
    public String uploadFile(MultipartFile File){

    }
}
