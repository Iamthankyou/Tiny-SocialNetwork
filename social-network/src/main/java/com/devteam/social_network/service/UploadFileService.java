package com.devteam.social_network.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFileService {
    public List<String> uploadFile(MultipartFile[] multipartFileList);

    public String uploadFileForChat(MultipartFile multipartFile);
}
