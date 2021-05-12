package com.devteam.social_network.controller;

import com.devteam.social_network.domain.PostComment;
import com.devteam.social_network.sdi.PostCommentSdi;
import com.devteam.social_network.service.PostCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @PostMapping("/insert-comment")
    @ApiOperation("insert-comment")
    public ResponseEntity<PostComment> insertComment(@RequestBody PostCommentSdi postCommentSdi){
        PostComment postComment = new PostComment();
        postComment.setPostId(postCommentSdi.getPostId());
        postComment.setContent(postCommentSdi.getContent());
        postComment.setUserEmail(postCommentSdi.getUserEmail());
        postComment.setCommentDate(LocalDate.now());
        postComment.setCommentTime(LocalTime.now());
        PostComment postComment1 = postCommentService.save(postComment);
        return ResponseEntity.status(HttpStatus.OK).body(postComment1);
    }

}
