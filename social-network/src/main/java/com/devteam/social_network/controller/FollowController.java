package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Friend;
import com.devteam.social_network.service.FriendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow-controller")
public class FollowController {
    @Autowired
    FriendService friendService;
    @PostMapping("/insert-follow")
    @ApiOperation("insert-follow")
    public ResponseEntity<Friend> insertFollowing(@RequestBody Friend friend){

        if (friendService.findAll().stream().anyMatch(f -> f.getUserEmail().equals(friend.getUserEmail())&&f.getFollowEmail().equals(friend.getFollowEmail()))){
            friendService.delete(friend);
        }
        return ResponseEntity.status(HttpStatus.OK).body(friendService.save(friend));
    }

}
