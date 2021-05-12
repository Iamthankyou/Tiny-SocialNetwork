package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Love;
import com.devteam.social_network.service.LoveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert-love")
    @ApiOperation("insert-love")
    public ResponseEntity<Love> insertLove(@RequestBody Love love){
        if(loveService.findAll().stream().anyMatch(l -> l.getPostId() == love.getPostId()&&l.getUserEmail().equals(love.getUserEmail()))){
            loveService.delete(love);
            return ResponseEntity.status(HttpStatus.OK).body(love);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loveService.save(love));
    }

    @DeleteMapping("/delete-love")
    @ApiOperation("delete-love")
    public ResponseEntity<String> deleteLove(@RequestBody Love love){
        loveService.delete(love);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
}
