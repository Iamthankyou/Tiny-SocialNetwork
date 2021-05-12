package com.devteam.social_network.service;

import com.devteam.social_network.domain.Love;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LoveService extends JpaRepository<Love,Long> {
}
