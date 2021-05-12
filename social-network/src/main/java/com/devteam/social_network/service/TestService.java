package com.devteam.social_network.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {
    public String uploadFile(MultipartFile multipartFile) throws IOException;
}
