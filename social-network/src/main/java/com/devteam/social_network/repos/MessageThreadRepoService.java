package com.devteam.social_network.repos;

import com.devteam.social_network.sdo.ListMessageInfoSdo;
import com.devteam.social_network.sdo.MessageInfoSdo;

import java.util.List;

public interface MessageThreadRepoService {
    public ListMessageInfoSdo getListMessageOfThreadMessage(Long threadId);
}
