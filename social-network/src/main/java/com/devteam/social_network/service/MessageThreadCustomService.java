package com.devteam.social_network.service;

import com.devteam.social_network.sdo.ConversationInfoSdo;
import com.devteam.social_network.sdo.ConversationSdo;
import com.devteam.social_network.sdo.MessageInfoSdo;

import java.util.List;

public interface MessageThreadCustomService {

    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId);
    public ConversationInfoSdo getListConversaiont(String userEmail, int pageIndex, int size);
}
