package com.devteam.social_network.service;

import com.devteam.social_network.domain.Account;

public interface AccountServiceCustom {
    public Account getAccountByEmail(String userEmail);
}
