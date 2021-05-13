package com.devteam.social_network.service;

import com.devteam.social_network.domain.MessageThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MessageThreadService extends JpaRepository<MessageThread,Long> {
}
