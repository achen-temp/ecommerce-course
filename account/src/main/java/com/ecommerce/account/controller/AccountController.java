package com.ecommerce.account.controller;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping({"/", "/home", "/info", "/account"})
    public Account getAccount() {
        return service.getAccountInfo();
    }

    @GetMapping("/balance_with_currency")
    public String checkBalanceWithCurrency() {
        return service.getCurrency();
    }

    @GetMapping("/balance")
    public double checkBalance() {
        return service.getBalance();
    }
}
