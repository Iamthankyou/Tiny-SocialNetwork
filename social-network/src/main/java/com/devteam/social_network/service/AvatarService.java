package com.devteam.social_network.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AvatarService {
    public List<String> uploadFile(MultipartFile[] multipartFileList);
}