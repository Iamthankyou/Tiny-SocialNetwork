package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Media;
import com.devteam.social_network.service.MediaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/media-controller")
public class MediaController {
    @Autowired
    MediaService mediaService;
    @GetMapping("/get-media-by-post-id")
    @ApiOperation("get-media-by-post-id")
    public ResponseEntity<List<Media>> getMediaByPostId(@RequestParam(required = true) Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findAll().stream().filter(m -> m.getPostId() == postId).collect(Collectors.toList()));
    }
}
