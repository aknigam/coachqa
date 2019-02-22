package com.coachqa.repository.dao;

import com.coachqa.entity.Account;

public interface AccountDAO {

    Account fetchAccountByName(String accountName);

    Account fetchCompleteAccountDetails(int accountId);
}
