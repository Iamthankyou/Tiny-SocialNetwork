package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.Date;

@Data
public class CreateThreadMessageSdo {

    private Long threadId;
    private String ownerEmail;
    private Date createAt;
    private Date updateAt;
    private String userEmail;
    private String avatar;
    private String firstName;
    private String lastName;
    private String nickName;
}
