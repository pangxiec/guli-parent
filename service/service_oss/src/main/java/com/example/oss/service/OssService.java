package com.example.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xmy
 * @date 2020/12/30 14:54
 */
public interface OssService {

    /**
     * 上传头像到 OSS
     *
     * @param file
     * @return
     */
    public String uploadFileAvatar(MultipartFile file);
}
