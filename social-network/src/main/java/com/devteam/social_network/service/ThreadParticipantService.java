package com.devteam.social_network.service;

import com.devteam.social_network.domain.ThreadParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ThreadParticipantService extends JpaRepository<ThreadParticipant,Long> {
}
