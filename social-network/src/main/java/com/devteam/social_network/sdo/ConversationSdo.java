package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConversationSdo {

    private String lastMessage;
    private Long threadId;
    private Date createAt;
    private Date updateAt;
    private List<String> listUserEmail;
    private Long total;
    private String avatar;
    private String firstName;
    private String lastName;
}
