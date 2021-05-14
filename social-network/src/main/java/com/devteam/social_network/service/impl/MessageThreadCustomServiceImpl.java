package com.devteam.social_network.service.impl;

import com.devteam.social_network.domain.Message;
import com.devteam.social_network.domain.MessageThread;
import com.devteam.social_network.domain.Post;
import com.devteam.social_network.domain.ThreadParticipant;
import com.devteam.social_network.repos.MessageThreadRepoService;
import com.devteam.social_network.sdo.ConversationSdo;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.service.MessageService;
import com.devteam.social_network.service.MessageThreadCustomService;
import com.devteam.social_network.service.MessageThreadService;
import com.devteam.social_network.service.ThreadParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageThreadCustomServiceImpl implements MessageThreadCustomService {
    @Autowired
    MessageThreadRepoService messageThreadRepoService;
    @Autowired
    MessageThreadService messageThreadService;
    @Autowired
    MessageService messageService;
    @Autowired
    ThreadParticipantService threadParticipantService;
    @Override
    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId) {
        return messageThreadRepoService.getListMessageOfThreadMessage(threadId);
    }

    @Override
    public List<ConversationSdo> getListConversaiont(String useEmail,int pageIndex, int size) {
        List<ConversationSdo> result = new ArrayList<>();
     List<MessageThread> list = messageThreadService.findAll(PageRequest.of(pageIndex,size, Sort.by("updateAt").descending()))
             .filter(mts -> mts.getOwnerEmail().equals(useEmail)).toList();
        list.forEach(l -> {
            ConversationSdo conversationSdo = new ConversationSdo();
            String lastMessage = "";
            List<Message> messageList = messageService.findAll().stream().filter(ms -> ms.getThreadId() == l.getThreadId())
                    .sorted((o1,o2) ->{
                        if (o1.getUpdateAt().after(o2.getUpdateAt())){
                            return -1;
                        }
                        if (o1.getUpdateAt().before(o2.getUpdateAt())){
                            return 1;
                        }
                        return 0;
                    }).collect(Collectors.toList());
            if(messageList.size() > 0 ){
                lastMessage = messageList.get(0).getContent();
            }
            List<String> listUser = threadParticipantService.findAll().stream().filter(tps -> tps.getThreadId() == l.getThreadId())
                    .map(tps -> tps.getUserEmail())
                    .collect(Collectors.toList());
            conversationSdo.setListUserEmail(listUser);
            conversationSdo.setCreateAt(l.getCreateAt());
            conversationSdo.setLastMessage(lastMessage);
            conversationSdo.setThreadId(l.getThreadId());
            conversationSdo.setUpdateAt(l.getUpdateAt());
            result.add(conversationSdo);
        });

        List<ThreadParticipant> list1 = threadParticipantService.findAll(PageRequest.of(pageIndex,size))
                .filter(mts -> mts.getUserEmail().equals(useEmail)).toList();
        list1.forEach(l -> {
            ConversationSdo conversationSdo = new ConversationSdo();
            String lastMessage = "";
            List<Message> messageList = messageService.findAll().stream().filter(ms -> ms.getThreadId() == l.getThreadId())
                    .sorted((o1,o2) ->{
                        if (o1.getUpdateAt().after(o2.getUpdateAt())){
                            return -1;
                        }
                        if (o1.getUpdateAt().before(o2.getUpdateAt())){
                            return 1;
                        }
                        return 0;
                    }).collect(Collectors.toList());
            if(messageList.size() > 0 ){
                lastMessage = messageList.get(0).getContent();
            }
            MessageThread messageThread = messageThreadService.findById(l.getThreadId()).orElse(null);
            List<String> listUser = new ArrayList<>();
            listUser.add(messageThread.getOwnerEmail());
            conversationSdo.setListUserEmail(listUser);
            conversationSdo.setCreateAt(messageThread.getCreateAt());
            conversationSdo.setLastMessage(lastMessage);
            conversationSdo.setThreadId(messageThread.getThreadId());
            conversationSdo.setUpdateAt(messageThread.getUpdateAt());
            result.add(conversationSdo);
        });
        return result;
    }
}
