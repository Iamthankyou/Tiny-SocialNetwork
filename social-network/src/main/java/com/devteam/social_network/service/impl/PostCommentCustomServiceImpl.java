package com.devteam.social_network.service.impl;

import com.devteam.social_network.service.PostCommentCustomService;
import org.springframework.stereotype.Repository;

@Repository
public class PostCommentCustomServiceImpl implements PostCommentCustomService {
    @Override
    public String deleteComment(Long postId, String commentDate, String commentTime, String userEmail) {
        return null;
    }
}
