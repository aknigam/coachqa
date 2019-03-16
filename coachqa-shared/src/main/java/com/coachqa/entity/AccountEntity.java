package com.coachqa.entity;

import java.io.Serializable;

public class AccountEntity implements Serializable {

    private Account account;

    public AccountEntity() {}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
