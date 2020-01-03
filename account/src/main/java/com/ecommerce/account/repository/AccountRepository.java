package com.ecommerce.account.repository;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.domain.Order;
import com.ecommerce.account.domain.Wallet;
import com.ecommerce.account.service.AccountService;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AccountRepository {

    private static Wallet wallet;
    private static Account account;
    private static Set<Order> orderHistory;

    static{
        orderHistory = new HashSet<>();

        wallet = new Wallet();
        wallet.setCurrency("$");
        wallet.setBalance(200.00);

        account = new Account();
        account.setId(1);
        account.setName("Joe Doe");
        account.setAddress("Princeton, NJ");
        account.setAccountBalance(wallet);
        account.setOrderHistory(null);
    }

    public Account getAccountInfo() {
        return account;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
