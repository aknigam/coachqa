package com.coachqa.service;

import com.coachqa.entity.Account;
import com.coachqa.repository.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDAO accountDao;

    @Override
    public Account createAccount(Account account) {
        return accountDao.createAccount(account);
    }
}
