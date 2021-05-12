package com.devteam.social_network.controller;

import com.devteam.social_network.domain.PostReaction;
import com.devteam.social_network.service.PostReactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/post-reaction-controller")
public class PostReactionController {

    @Autowired
    PostReactionService postReactionService;
    @GetMapping("/get-post-reaction-by-post-id")
    @ApiOperation("get-post-reaction-by-post-id")
    public ResponseEntity<PostReaction> getPostReactionByPostId(@RequestParam Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(postReactionService.findAll().stream().filter(p -> p.getPostId() == postId).collect(Collectors.toList()).get(0));
    }
}
