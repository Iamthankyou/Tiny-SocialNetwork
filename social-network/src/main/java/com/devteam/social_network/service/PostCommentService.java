package com.devteam.social_network.service;

import com.devteam.social_network.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PostCommentService extends JpaRepository<PostComment,Long> {
}
