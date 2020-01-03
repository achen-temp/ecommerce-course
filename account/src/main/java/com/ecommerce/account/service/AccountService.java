package com.ecommerce.account.service;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public Account getAccountInfo() {
        return repository.getAccountInfo();
    }

    public double getBalance() {
        return repository.getWallet().getBalance();
    }

    public String getCurrency() {
        return repository.getWallet().getCurrency();
    }
}
