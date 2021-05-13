package com.devteam.social_network.service.impl;

import com.devteam.social_network.repos.MessageThreadRepoService;
import com.devteam.social_network.sdo.MessageInfoSdo;
import com.devteam.social_network.service.MessageThreadCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageThreadCustomServiceImpl implements MessageThreadCustomService {
    @Autowired
    MessageThreadRepoService messageThreadRepoService;
    @Override
    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId) {
        return messageThreadRepoService.getListMessageOfThreadMessage(threadId);
    }
}
