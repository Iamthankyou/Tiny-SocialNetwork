package com.devteam.social_network.sdo;

import com.devteam.social_network.domain.Love;
import com.devteam.social_network.domain.PostComment;
import com.devteam.social_network.domain.PostReaction;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class PostSdo {
    private Long postId;
    private String content;
    private LocalDate postDate;
    private LocalTime postTime;
    private String userEmail;
    private String reactionType;
}
