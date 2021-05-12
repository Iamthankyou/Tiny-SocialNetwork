package com.devteam.social_network.service.impl;

import com.devteam.social_network.common.exception.AppException;
import com.devteam.social_network.service.AvatarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvartarServiceImpl implements AvatarService {
    @Override
    public List<String> uploadFile(MultipartFile[] multipartFileList) {
        List<String> result = new ArrayList<>();
        String root = "http://localhost:8998/file/images/";
        for (int i = 0 ; i < multipartFileList.length ; i++){
            if (multipartFileList[i].isEmpty()){
                throw new AppException("APIUF", "file not empty") {
                    @Override
                    public int getHttpErrorCode() {
                        return 0;
                    }
                };
            }
        }
//        File folderUpload = new File(System.getProperty("user.home") + "/Uploads/");
//        System.out.print(folderUpload.toString());

        Path path = Paths.get(System.getProperty("user.dir"));
        path = Paths.get(path.toString(), "../").normalize().resolve("Uploads");

//        Path path = folderUpload.toPath();

        for (int i = 0 ; i < multipartFileList.length ; i++){
            try {
                InputStream inputStream = multipartFileList[i].getInputStream();
//                Files.copy(inputStream,folderUpload.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(path.resolve(multipartFileList[i].getOriginalFilename()).toString());
                Files.copy(inputStream,path.resolve(multipartFileList[i].getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                result.add(root+multipartFileList[i].getOriginalFilename());
            } catch (IOException e) {
                throw new AppException("APIUL2", "Can't upload") {
                    @Override
                    public int getHttpErrorCode() {
                        return 0;
                    }
                };
            }
        }
        return result;
    }
}
