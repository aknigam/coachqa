package com.coachqa.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {

    private int accountId;

    private String accountName;

    private String accountDescription;

    private List<AccountPreference> accountPreferences;

    public  Account(){}

    private static Map<Integer, Account> accounts = new HashMap<>();

    private static Object mutex = new Object();

    public static Account getById(int accountId) {


        Account a = accounts.get(accountId);
        if(a != null) {
            return a;
        }
        synchronized (mutex) {
            a = accounts.get(accountId);
            if(a != null) {
                return a;
            }
            a = new Account();
            a.setAccountId(accountId);
            accounts.put(accountId , a);

        }
        return a;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public List<AccountPreference> getAccountPreferences() {
        return accountPreferences;
    }

    public void setAccountPreferences(List<AccountPreference> accountPreferences) {
        this.accountPreferences = accountPreferences;
    }

    public boolean requiresPostApproval() {
        // todo should check the account preference here
        return true;
    }
}
