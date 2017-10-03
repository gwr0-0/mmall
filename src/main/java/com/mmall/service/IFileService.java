package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gwr0-0 on 2017/9/29.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
