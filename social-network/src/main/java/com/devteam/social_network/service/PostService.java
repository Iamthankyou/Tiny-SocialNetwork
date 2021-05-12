package com.devteam.social_network.service;

import com.devteam.social_network.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PostService extends JpaRepository<Post,Long> {
}
