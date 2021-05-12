package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Love;
import com.devteam.social_network.service.LoveService;
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
@RequestMapping("/love-controller")
public class LoveController {
    @Autowired
    LoveService loveService;

    @GetMapping("/get-list-love-by-post-id")
    @ApiOperation("get-list-love-by-post-id")
    public ResponseEntity<List<Love>> getListLoveByPostId(@RequestParam(required = true) Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(loveService.findAll().stream().filter(l -> l.getPostId() == postId).collect(Collectors.toList()));
    }
}
