package com.devteam.social_network.controller;

import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PostSdo;
import com.devteam.social_network.service.PostServiceCustom;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post-controller")
public class PostController {
    @Autowired
    PostServiceCustom postServiceCustom;
    @PostMapping("/post")
    @ApiOperation("post")
    public ResponseEntity<PostSdo> post(@RequestBody PostSdi postSdi){
        return ResponseEntity.status(HttpStatus.OK).body(postServiceCustom.post(postSdi));
    }
}
