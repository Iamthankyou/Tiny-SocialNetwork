package com.devteam.social_network.sdi;

import lombok.Data;

@Data
public class MessageDbSdi {

    private Long threadId;
    private String sender;
    private String content;
    private String type;
}
