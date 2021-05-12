package com.devteam.social_network.sdo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PostSdo {
    private Long postId;
    private String content;
    private LocalDate postDate;
    private LocalTime postTime;
    private String userEmail;
    private String reactionType;
}
