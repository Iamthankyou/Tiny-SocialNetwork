package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.List;

@Data
public class ConversationInfoSdo {
    private List<ConversationSdo> conversationSdoList;
    private int total;
}
