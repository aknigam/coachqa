package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Account;
import com.coachqa.repository.dao.AccountDAO;
import com.coachqa.repository.dao.mybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DBAccountDao implements AccountDAO {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account fetchAccountByName(String accountName) {
        return accountMapper.getByName(accountName);
    }

    @Override
    public Account fetchCompleteAccountDetails(int accountId) {
        return accountMapper.fetchCompleteAccountDetails(accountId);
    }

    @Override
    public Account createAccount(Account account) {
        int accountId =  accountMapper.createAccount(account);
        account.setAccountId(accountId);
        return account;
    }
}
