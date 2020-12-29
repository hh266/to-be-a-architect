package com.zch.fastdfs.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zch
 * @date 2020/12/29 17:44
 */
@Api(value = "文件管理", tags = {"文件相关的接口"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FastFileStorageClient storageClient;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", httpMethod="POST")
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), file.getOriginalFilename(), null);
        return  storePath.getFullPath();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "文件删除", httpMethod="POST")
    public String deleteFile(String filePath) {
        storageClient.deleteFile(filePath);
        return "OK";
    }
}
