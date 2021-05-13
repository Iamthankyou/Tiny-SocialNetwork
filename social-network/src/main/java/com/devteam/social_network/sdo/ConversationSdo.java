package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.Date;

@Data
public class ConversationSdo {

    private String lastMessage;
    private Long threadId;
    private Date createAt;
    private Date updateAt;
}
