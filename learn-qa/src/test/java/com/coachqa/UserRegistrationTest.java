package com.coachqa;

import com.coachqa.entity.Account;
import com.coachqa.entity.AppUser;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


public class UserRegistrationTest extends BaseControllorTest{


    @Test
    @Ignore
    public void testPing() throws Exception {

        HttpHeaders headers = getAuthHttpHeaders("bearer " + bearer);
        ResponseEntity<String> e = this.restTemplate.exchange("http://localhost:" + port + "/api/users/ping",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        assertThat(e.getBody()).isNotNull();
        assertThat(e.getBody()).contains("TRUE");

    }

    @Test
    public void testAccountCreationAndUserRegistration(){

        Account account = new Account();
        account.setAccountName("test01");
        account.setAccountDescription("Test automation account");

        account = createAccount(account);

        assertThat(account.getAccountId()).isNotNull();
        assertThat(account.getAccountId()).isNotEqualTo(0);

        // account admin user should be created
        AppUser accountAdmin = getAccountAdmin();
        accountAdmin =  addUser(accountAdmin);
        assertThat(accountAdmin).isNull();

        // create classroom

        // create classroom admins

        // add tags if needed

    }

    private AppUser getAccountAdmin() {
        return null;
    }


}
