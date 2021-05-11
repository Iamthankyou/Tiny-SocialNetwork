package com.devteam.social_network.service.impl;

import com.devteam.social_network.domain.Account;
import com.devteam.social_network.service.AccountService;
import com.devteam.social_network.service.AccountServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceCustomImpl implements AccountServiceCustom {
    @Autowired
    AccountService accountService;
    @Override
    public Account getAccountByEmail(String userEmail) {
        return accountService.findById(userEmail).orElse(null);
    }
}
