package com.devteam.social_network.service;

import com.devteam.social_network.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MessageService extends JpaRepository<Message,Long> {
}
