package com.devteam.social_network.service;

import com.devteam.social_network.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FriendService extends JpaRepository<Friend,String> {
}
