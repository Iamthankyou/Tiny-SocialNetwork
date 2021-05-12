package com.devteam.social_network.composite;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PostCommentCompositeKey implements Serializable {
    private Long postId;
//    private LocalTime commentTime;
//    private LocalDate commentDate;
    private String userEmail;
}
