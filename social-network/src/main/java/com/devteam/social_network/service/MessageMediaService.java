package com.devteam.social_network.service;

import com.devteam.social_network.domain.MessageMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MessageMediaService extends JpaRepository<MessageMedia,Long> {
}
