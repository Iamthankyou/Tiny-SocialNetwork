package com.devteam.social_network.controller;

import com.devteam.social_network.domain.ThreadParticipant;
import com.devteam.social_network.service.ThreadParticipantService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread-participant-controller")
public class ThreadParticipantController {

    @Autowired
    ThreadParticipantService threadParticipantService;

    @PostMapping("/insert-thread-participant")
    @ApiOperation("/insert-thread-participant")
    public ResponseEntity<ThreadParticipant> insertThreadParticipant(@RequestBody ThreadParticipant threadParticipant){
        return ResponseEntity.status(HttpStatus.OK).body(threadParticipantService.save(threadParticipant));
    }
}
