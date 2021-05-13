package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Media;
import com.devteam.social_network.service.MediaService;
import com.devteam.social_network.service.UploadFileService;
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
@RequestMapping("/upload-file-controller")
public class UploadFileController {
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    MediaService mediaService;
    @PostMapping("upload-file")
    @ApiOperation("upload-file")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] multipartFile,@RequestParam String type,@RequestParam String caption,@RequestParam Long postId){
        String root = "http://localhost:8998/upload-file-controller/images/";
        for (int i = 0 ; i < multipartFile.length ; i++){
            Media media = new Media();
            media.setType(type);
            media.setFileName(root+multipartFile[i].getOriginalFilename());
            media.setCaption(caption+"@"+multipartFile[i].getOriginalFilename());
            media.setPostId(postId);
            mediaService.save(media);
        }
        return ResponseEntity.status(HttpStatus.OK).body(uploadFileService.uploadFile(multipartFile));
    }

    public ResponseEntity<String> uploadFileForChat(@RequestParam("files") MultipartFile multipartFile,@RequestParam Long messageId){
        String root = "http://localhost:8998/upload-file-controller/images/";
        return ResponseEntity.status(HttpStatus.OK).body(uploadFileService.uploadFileForChat(multipartFile));
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
