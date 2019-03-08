package com.coachqa;

import com.coachqa.entity.Account;
import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/*
Refer: https://spring.io/guides/gs/testing-web/
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnQAWebConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class BaseControllorTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;
    protected String bearer;


    @Before
    public void setAuthHeader(){

        HttpHeaders headers = getAuthHttpHeaders("Basic Zm9vOmJhcg==");

        String  url = "http://localhost:" + port + "/oauth/token?grant_type=password&scope=read" +
                "&username" +
                "=anigam@expedia.com&password=pass";
        ResponseEntity<Map> e = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), Map.class);
        bearer = (String) e.getBody(). get("access_token");

    }



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


    protected HttpHeaders getAuthHttpHeaders() {
        return getAuthHttpHeaders("bearer " + bearer);
    }

    protected HttpHeaders getAuthHttpHeaders(String s) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", s);
        return headers;
    }

    // API end points

    protected ResponseEntity<Question> submitQuestion(HttpEntity<Object> entity) {
        return this.restTemplate.exchange("http://localhost:" + port + "/api/questions",
                HttpMethod.POST,
                entity,
                Question.class);
    }

    protected Account createAccount(Account account) {
        HttpEntity<Object> entity = new HttpEntity<>(account, getAuthHttpHeaders());
        ResponseEntity<Account> response = this.restTemplate.exchange("http://localhost:" + port + "/api/account",
                HttpMethod.POST,
                entity,
                Account.class);
        return  response.getBody();
    }


    protected AppUser addUser(AppUser accountAdmin) {
        HttpEntity<Object> entity = new HttpEntity<>(accountAdmin, getAuthHttpHeaders());
        ResponseEntity<AppUser> response = this.restTemplate.exchange("http://localhost:" + port + "/api/users",
                HttpMethod.POST,
                entity,
                AppUser.class);
        return  response.getBody();
    }


}
