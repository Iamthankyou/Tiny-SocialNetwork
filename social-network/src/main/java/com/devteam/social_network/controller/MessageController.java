package com.devteam.social_network.controller;


import com.devteam.social_network.domain.Message;
import com.devteam.social_network.domain.MessageInfoSdi;
import com.devteam.social_network.domain.MessageThread;
import com.devteam.social_network.domain.PostComment;
import com.devteam.social_network.sdi.MessageSdi;
import com.devteam.social_network.sdi.PostCommentSdi;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.sdo.MessageSdo;
import com.devteam.social_network.service.MessageService;
import com.devteam.social_network.service.MessageThreadService;
import com.devteam.social_network.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@RestController
public class MessageController {

    @Autowired
    PostCommentService postCommentService;

    @Autowired
    MessageThreadService messageThreadService;

    @Autowired
    MessageService messageService;
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public PostComment send(PostCommentSdi postCommentSdi) throws Exception{
        PostComment postComment = new PostComment();
        postComment.setUserEmail(postCommentSdi.getUserEmail());
        postComment.setContent(postCommentSdi.getContent());
        postComment.setPostId(postCommentSdi.getPostId());
        postComment.setCommentDate(LocalDate.now());
        postComment.setCommentTime(LocalTime.now());
        return postCommentService.save(postComment);
    }

    @MessageMapping("/send-message")
    @SendTo("/topic/message-info")
    public Message sendMessage(MessageInfoSdi messageInfoSdi){
        Message message = new Message();
        message.setThreadId(messageInfoSdi.getThreadId());
        message.setSender(messageInfoSdi.getSender());
        message.setCreateAt(new Date());
        message.setUpdateAt(new Date());
        message.setType(messageInfoSdi.getType());
        message.setContent(messageInfoSdi.getContent());
        MessageThread messageThread = messageThreadService.findById(message.getThreadId()).orElse(null);
        messageThread.setUpdateAt(message.getUpdateAt());
        messageThreadService.save(messageThread);
        if ("heart".equals(messageInfoSdi.getType().trim().toLowerCase())){
            message.setContent("https://bit.ly/33DzUKs");
        }
        return messageService.save(message);
    }
}
