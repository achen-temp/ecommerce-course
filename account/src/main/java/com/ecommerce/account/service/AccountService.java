package com.ecommerce.account.service;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    Environment environment;

    public Account getAccountInfo() {
        return repository.getAccountInfo();
    }

    public double getBalance() {
        return repository.getWallet().getBalance();
    }

    public String getCurrency() {
        return repository.getWallet().getCurrency();
    }

    public String getServerInfo() {
        return environment.getProperty("server.port");
    }

    public void pay(double bill) {
        double currentTotalBalance = repository.getWallet().getBalance();
        repository.getWallet().setBalance(currentTotalBalance - bill);
        System.out.println("total balance is reduced by " + bill);
    }
}
