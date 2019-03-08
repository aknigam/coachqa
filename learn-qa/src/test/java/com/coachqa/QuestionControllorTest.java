package com.coachqa;

import com.coachqa.entity.Question;
import com.coachqa.entity.Tag;
import com.coachqa.ws.controllor.QuestionControllor;
import notification.EventNotifier;
import notification.notifier.LoggingNotifier;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllorTest  extends BaseControllorTest {

    private int questionNumber;

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


    @Test
    @Ignore
    public void testSubmitQuestion() throws Exception {


        Question question = getQuestion();

        HttpEntity<Object> entity = new HttpEntity<>(question, getAuthHttpHeaders());

        ResponseEntity<Question> e = submitQuestion(entity);

        Question q = e.getBody();

        assertThat(q).isNotNull();
        assertThat(q.getQuestionId()).isNotNull();
        assertThat(q.getAccount().getAccountId()).isEqualTo(100);


        // validate that the even is generated and sent
        LoggingNotifier n = LoggingNotifier.getInstance();
        assertThat(n.getMessages().size()).isEqualTo(1);


    }



    private Question getQuestion() {
        Question question = new Question();
        question.setContent(++questionNumber + "test content");
        question.setClassroomId(1);
        question.setRefSubjectId(1);
        question.setTags(Collections.singletonList(new Tag(18)));
        return question;
    }



}
