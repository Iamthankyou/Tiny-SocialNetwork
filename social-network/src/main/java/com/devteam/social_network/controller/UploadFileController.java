package com.devteam.social_network.controller;

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
    @PostMapping("upload-file")
    @ApiOperation("upload-file")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] multipartFile){
        return ResponseEntity.status(HttpStatus.OK).body(uploadFileService.uploadFile(multipartFile));
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
