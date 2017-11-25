package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by gwr0-0 on 2017/9/29.
 */
@Slf4j
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名，+1为了不显示.
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //用uuid对文件进行重命名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件，上传文件名：{}，路径：{}，新文件名：{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdir();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //将targetFile上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传成功后，删除upload下的文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常：", e);
        }
        return targetFile.getName();
    }

}
