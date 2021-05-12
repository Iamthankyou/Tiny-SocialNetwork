package com.devteam.social_network.controller;

import com.devteam.social_network.domain.User;
import com.devteam.social_network.repos.UserRepository;
import com.devteam.social_network.service.AvatarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class AvatarController {
    @Autowired
    AvatarService avatarService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("upload-file")
    @ApiOperation("upload-file")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] multipartFile,@RequestParam String type,@RequestParam String username){
        String root = "http://localhost:8998/upload-file-controller/images/";
        User user = userRepository.findByEmail(username);
        for (int i = 0 ; i < multipartFile.length ; i++){
            user.setAvatar(root+multipartFile[i].getOriginalFilename());
        }

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(avatarService.uploadFile(multipartFile));
    }

    @GetMapping("images/{photo}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo")String photo){
        if(!photo.equals("") || photo != null){
            try{
                Path filename = Paths.get("upload",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }
}
