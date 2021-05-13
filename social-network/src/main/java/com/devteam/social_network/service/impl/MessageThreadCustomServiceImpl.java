package com.devteam.social_network.service.impl;

import com.devteam.social_network.domain.MessageThread;
import com.devteam.social_network.domain.Post;
import com.devteam.social_network.repos.MessageThreadRepoService;
import com.devteam.social_network.sdo.ConversationSdo;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.service.MessageService;
import com.devteam.social_network.service.MessageThreadCustomService;
import com.devteam.social_network.service.MessageThreadService;
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
    @Override
    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId) {
        return messageThreadRepoService.getListMessageOfThreadMessage(threadId);
    }

    @Override
    public List<ConversationSdo> getListConversaiont(Long threadId, int pageIndex, int size) {
        List<ConversationSdo> result = new ArrayList<>();
        List<MessageThread> list = messageThreadService.findAll(PageRequest.of(pageIndex,size, Sort.by("updateAt").descending())).toList();
        list.forEach(l -> {
            ConversationSdo conversationSdo = new ConversationSdo();
            String lastMessage = messageService.findAll().stream().filter(ms -> ms.getThreadId() == l.getThreadId())
                    .sorted((o1,o2) ->{
                        if (o1.getUpdateAt().after(o2.getUpdateAt())){
                            return -1;
                        }
                        if (o1.getUpdateAt().before(o2.getUpdateAt())){
                            return 1;
                        }
                        return 0;
                    }).collect(Collectors.toList()).get(0).getUpdateAt().toString();
            conversationSdo.setCreateAt(l.getCreateAt());
            conversationSdo.setLastMessage(lastMessage);
            conversationSdo.setThreadId(l.getThreadId());
            conversationSdo.setUpdateAt(l.getUpdateAt());
            result.add(conversationSdo);
        });
        return result;
    }
}
