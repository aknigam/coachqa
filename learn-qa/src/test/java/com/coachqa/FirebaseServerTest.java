package com.coachqa;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by a.nigam on 14/04/17.
 */
public class FirebaseServerTest {

    @Test
    public void sendHttpMessage() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // server key
        httpHeaders.set("Authorization", "key=AAAAq1eI8gU:APA91bEPsL_crUcopXCWOsQCvQ1f_gzmR3bfCvvUyXx2ARLH-vNAfmO140KdjicSvxXCaEJcO6fi-ugLe7EtTecbKOy1brKHS0n4hqWB2yqtSG55A5p_b7DLwo3ynYO6eSFyBPyloKC4");

        // device token linked to the user
        HttpEntity<?> httpEntity = new HttpEntity<>("{ \"to\" : \"fZ8kyXfSVTM:APA91bHyx5fsye_ZLXFMphJKJ-qH5n_VkwekcOVClFOHse0j2Jpme0COchqv4DcbznfPDVW6E7OQdWURxgFUNsUcNUWc3IMuHWSJ9jyFhTUPYWDgJu0T_fOKRrP5yq4YW5HuTzponFUm\" }", httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity("https://fcm.googleapis.com/fcm/send", httpEntity, Map.class);
        System.out.println(response.getBody());

    }
}
