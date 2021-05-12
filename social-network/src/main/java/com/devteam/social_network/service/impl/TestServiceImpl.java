package com.devteam.social_network.service.impl;

import com.devteam.social_network.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(new File("D:\\FileUpload\\"+multipartFile.getOriginalFilename()));
        return "D:\\FileUpload";
    }
}
