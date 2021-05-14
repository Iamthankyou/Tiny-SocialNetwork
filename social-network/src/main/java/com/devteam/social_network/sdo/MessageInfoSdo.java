package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.Date;

@Data
public class MessageInfoSdo {
    private Long threadId;
    private Long messageId;
    private String content;
    private Date createAt;
    private Date updateAt;
    private String type;
    private String sender;
    private String avatar;
}
