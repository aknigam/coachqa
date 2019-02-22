package com.coachqa;

import com.coachqa.ws.controllor.QuestionControllor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/*
Refer: https://spring.io/guides/gs/testing-web/
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnQAWebConfig.class)
public class ApplicationTest {

    @Autowired
    private QuestionControllor questionControllor;

    @Test
    public void contextLoads() throws Exception {
        assertThat(questionControllor).isNotNull();
    }

}
