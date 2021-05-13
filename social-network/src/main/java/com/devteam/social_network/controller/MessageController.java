package com.devteam.social_network.controller;


import com.devteam.social_network.domain.PostComment;
import com.devteam.social_network.sdi.MessageSdi;
import com.devteam.social_network.sdi.PostCommentSdi;
import com.devteam.social_network.sdo.MessageSdo;
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
}
