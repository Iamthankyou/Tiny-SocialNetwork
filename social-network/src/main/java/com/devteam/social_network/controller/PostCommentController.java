package com.devteam.social_network.controller;

import com.devteam.social_network.domain.PostComment;
import com.devteam.social_network.service.PostCommentService;
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
@RequestMapping("/post-comment-controller")
public class PostCommentController {
    @Autowired
    PostCommentService postCommentService;

    @GetMapping("/get-list-comment-by-post-id")
    @ApiOperation("get-list-comment-by-post-id")
    public ResponseEntity<List<PostComment>> getListCommentByPostId(@RequestParam(required = true) Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(postCommentService.findAll().stream().filter(pc -> pc.getPostId() == postId).collect(Collectors.toList()));
    }
}
