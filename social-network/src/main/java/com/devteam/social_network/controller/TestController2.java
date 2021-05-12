package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Media;
import com.devteam.social_network.domain.PostReaction;
import com.devteam.social_network.service.MediaService;
import com.devteam.social_network.service.PostReactionService;
import com.devteam.social_network.service.PostService;
import com.devteam.social_network.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/test-controller")
public class TestController2 {

//    @Autowired
//    TestService testService;

    @Autowired
    MediaService mediaService;

    @Autowired
    PostReactionService postReactionService;

    @Autowired
    PostService postService;
    @GetMapping("/test")
    @ApiOperation("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }

//    @PostMapping("/upload")
//    @ApiOperation("upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
//        return ResponseEntity.status(HttpStatus.OK).body(testService.uploadFile(multipartFile));
//    }

    @PostMapping("/insert-media")
    @ApiOperation("insert-media")
    public ResponseEntity<Media> insertMedia(@RequestBody Media media){
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.save(media));
    }

    @PostMapping("/insert-post-reaction")
    @ApiOperation("insert-post-reaction")
    public ResponseEntity<PostReaction> insertPostReaction(@RequestBody PostReaction postReaction){
        return ResponseEntity.status(HttpStatus.OK).body(postReactionService.save(postReaction));
    }
}
