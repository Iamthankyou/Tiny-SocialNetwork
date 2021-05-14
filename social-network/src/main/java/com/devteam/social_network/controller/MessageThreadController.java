package com.devteam.social_network.controller;

import com.devteam.social_network.domain.MessageThread;
import com.devteam.social_network.domain.ThreadParticipant;
import com.devteam.social_network.sdi.CreateThreadMessageSdi;
import com.devteam.social_network.sdi.MessageThreadSdi;
import com.devteam.social_network.sdo.ConversationSdo;
import com.devteam.social_network.sdo.CreateThreadMessageSdo;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.service.MessageThreadCustomService;
import com.devteam.social_network.service.MessageThreadService;
import com.devteam.social_network.service.ThreadParticipantService;
import com.sun.jmx.snmp.tasks.ThreadService;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message-thread-controller")
public class MessageThreadController {

    @Autowired
    MessageThreadService messageThreadService;

    @Autowired
    ThreadParticipantService threadParticipantService;

    @Autowired
    MessageThreadCustomService messageThreadCustomService;

    @PostMapping("/insert-message-thread")
    @ApiOperation("insert-message-thread")
    public ResponseEntity<MessageThread> insertMessageThread(@RequestBody MessageThreadSdi messageThreadSdi){
        MessageThread messageThread = new MessageThread();
        messageThread.setCreateAt(new Date());
        messageThread.setOwnerEmail(messageThreadSdi.getOwnerEmail());
        return ResponseEntity.status(HttpStatus.OK).body(messageThreadService.save(messageThread));
    }

    @PostMapping("/create-conversation")
    @ApiOperation("create-converstation")
    public ResponseEntity<CreateThreadMessageSdo> createThreadMessage(@RequestBody CreateThreadMessageSdi createThreadMessageSdi){
        List<MessageThread> listMessageThreadOwner = messageThreadService.findAll()
                .stream()
                .filter(lmto -> lmto.getOwnerEmail().equals(createThreadMessageSdi.getOwnerEmail()))
                .collect(Collectors.toList());

        for (MessageThread messageThread:listMessageThreadOwner){
            List<ThreadParticipant> list = threadParticipantService.findAll()
                    .stream()
                    .filter(tps -> tps.getThreadId() == messageThread.getThreadId())
                    .collect(Collectors.toList());

            if (list.size() == 1){
                System.out.println(list.get(0).getUserEmail());
                if (list.get(0).getUserEmail().equals( createThreadMessageSdi.getUserEmail())){
                    System.out.println("a");
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                }
            }
        }


        listMessageThreadOwner = messageThreadService.findAll()
                .stream()
                .filter(lmto -> lmto.getOwnerEmail().equals(createThreadMessageSdi.getUserEmail()))
                .collect(Collectors.toList());

        for (MessageThread messageThread:listMessageThreadOwner){
            List<ThreadParticipant> list = threadParticipantService.findAll()
                    .stream()
                    .filter(tps -> tps.getThreadId() == messageThread.getThreadId())
                    .collect(Collectors.toList());
            if (list.size() == 1){
                if (list.get(0).getUserEmail() == createThreadMessageSdi.getOwnerEmail()){
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                }
            }
        }

        MessageThread messageThread = new MessageThread();
        messageThread.setOwnerEmail(createThreadMessageSdi.getOwnerEmail());
        messageThread.setCreateAt(new Date());
        messageThread = messageThreadService.save(messageThread);

        ThreadParticipant threadParticipant = new ThreadParticipant();
        threadParticipant.setThreadId(messageThread.getThreadId());
        threadParticipant.setUserEmail(createThreadMessageSdi.getUserEmail());
        threadParticipant = threadParticipantService.save(threadParticipant);

        CreateThreadMessageSdo createThreadMessageSdo = new CreateThreadMessageSdo();
        createThreadMessageSdo.setCreateAt(messageThread.getCreateAt());
        createThreadMessageSdo.setOwnerEmail(messageThread.getOwnerEmail());
        createThreadMessageSdo.setThreadId(messageThread.getThreadId());
        createThreadMessageSdo.setUpdateAt(messageThread.getUpdateAt());
        createThreadMessageSdo.setUserEmail(threadParticipant.getUserEmail());

        return ResponseEntity.status(HttpStatus.OK).body(createThreadMessageSdo);
    }


    @GetMapping("/get-list-message")
    @ApiOperation("get-list-message")
    public ResponseEntity<List<MessageInfoSdo>> getListMessageOfThreadMessage(@RequestParam Long threadId){
        return ResponseEntity.status(HttpStatus.OK).body(messageThreadCustomService.getListMessageOfThreadMessage(threadId));
    }

    @GetMapping("/get-list-conversation")
    @ApiOperation("get-list-conversation")
    public ResponseEntity<List<ConversationSdo>> getListConversation(@RequestParam int pageIndex, @RequestParam int size){
        return ResponseEntity.status(HttpStatus.OK).body(messageThreadCustomService.getListConversaiont(pageIndex,size));
    }
}