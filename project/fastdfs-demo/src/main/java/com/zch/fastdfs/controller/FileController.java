package com.zch.fastdfs.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/")
    @ApiOperation(value = "文件上传", httpMethod="POST")
    public String uploadFile(MultipartFile file) throws IOException {

        //获取文件后缀名
        String[] fileNameArr = file.getOriginalFilename().split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];

        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), suffix, null);
        return  storePath.getFullPath();
    }

    @DeleteMapping("/")
    @ApiOperation(value = "文件删除", httpMethod="DELETE")
    public String deleteFile(String filePath) {
        storageClient.deleteFile(filePath);
        return "OK";
    }
}
