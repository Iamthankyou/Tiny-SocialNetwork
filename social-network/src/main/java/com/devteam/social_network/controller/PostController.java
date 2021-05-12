package com.devteam.social_network.controller;

import com.devteam.social_network.common.PagedResponse;
import com.devteam.social_network.common.annotation.ApiPageable;
import com.devteam.social_network.domain.Post;
import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PagePost;
import com.devteam.social_network.sdo.PageableSdo;
import com.devteam.social_network.sdo.PostCustomSdo;
import com.devteam.social_network.sdo.PostSdo;
import com.devteam.social_network.service.PostServiceCustom;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    @GetMapping("/list-post")
    @ApiOperation("list-post")
    @ApiPageable
    public ResponseEntity<PagedResponse<PostCustomSdo>> listPost(@ApiIgnore @PageableDefault Pageable pageableRequest){
        return ResponseEntity.ok(PagedResponse.builder().page(postServiceCustom.listPost(pageableRequest)).build());
    }

    @GetMapping("/list-post-ver2")
    @ApiOperation("list-post-ver2")
    public ResponseEntity<PageableSdo> listPostVer2(@RequestParam int pageIndex, @RequestParam int size){
        return ResponseEntity.status(HttpStatus.OK).body(postServiceCustom.listPostVer2(pageIndex,size));
    }
}
