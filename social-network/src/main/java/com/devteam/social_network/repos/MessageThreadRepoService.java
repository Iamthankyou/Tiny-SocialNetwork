package com.devteam.social_network.repos;

import com.devteam.social_network.sdo.MessageInfoSdo;

import java.util.List;

public interface MessageThreadRepoService {
    public List<MessageInfoSdo> getListMessageOfThreadMessage(Long threadId);
}
