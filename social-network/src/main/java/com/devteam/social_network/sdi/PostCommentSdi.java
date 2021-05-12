package com.devteam.social_network.sdi;

import lombok.Data;

@Data
public class PostCommentSdi {
    private Long postId;
    private String content;
    private String userEmail;
}
