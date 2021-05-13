package com.devteam.social_network.controller;

import com.devteam.social_network.domain.Message;
import com.devteam.social_network.sdi.MessageDbSdi;
import com.devteam.social_network.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/message-db-controller")
public class MessageDbController {

    @Autowired
    MessageService messageService;

    @PostMapping("/insert-message")
    @ApiOperation("insert-message")
    public ResponseEntity<Message> insertMessage(@RequestBody MessageDbSdi messageDbSdi){
        Message message = new Message();
        message.setContent(messageDbSdi.getContent());
        message.setCreateAt(new Date());
        message.setSender(messageDbSdi.getSender());
        message.setThreadId(messageDbSdi.getThreadId());
        message.setType(messageDbSdi.getType());
        return ResponseEntity.status(HttpStatus.OK).body(messageService.save(message));
    }


}
