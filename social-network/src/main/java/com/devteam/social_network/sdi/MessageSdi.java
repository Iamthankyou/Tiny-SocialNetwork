package com.devteam.social_network.sdi;

import lombok.Data;

@Data
public class MessageSdi {
    private String from;
    private String text;
    private Long postId;
}
