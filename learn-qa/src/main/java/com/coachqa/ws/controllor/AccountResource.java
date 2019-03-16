package com.coachqa.ws.controllor;


import com.coachqa.entity.Account;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.UserTypeEnum;
import com.coachqa.service.AccountService;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * todo
 * 1. Rename AppUser table to contact table
 * 2. Create new application user table where the mapping with account and other details can be maintained. Password
 * will be stored here. Same contact can be present in more than one account.
 *
 * contact table created.
 *
 * 3. need to create an internal account where the application_admin can be allocated.
 * - internal_account . This name can be hardcoded. Users can not be created in this org. This needs DB entry
 *
 *
 *
 *
 *
 *
 */
@RestController
@RequestMapping("/api/account")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping
    public Account create(@RequestBody Account account){

        AppUser user = WSUtil.getUser(userService);
        // TODO: 05/03/19 add a check to validate that the user is 'application_admin'
        if(user.getUserType() == UserTypeEnum.application_admin) {
            return accountService.createAccount(account);
        }
        throw new RuntimeException("Only application admin can create new accounts");
    }
}
