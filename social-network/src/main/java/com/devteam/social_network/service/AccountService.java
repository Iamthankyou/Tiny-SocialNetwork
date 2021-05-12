package com.devteam.social_network.service;

import com.devteam.social_network.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends JpaRepository<Account,String> {
}
