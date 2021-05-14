package com.devteam.social_network.domain;

import lombok.Data;

@Data
public class MessageInfoSdi {

    private Long threadId;
    private String sender;
    private String content;
    private String type;
}
