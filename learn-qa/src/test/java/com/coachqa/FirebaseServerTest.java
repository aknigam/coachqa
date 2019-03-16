package com.coachqa;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**

 Created by a.nigam on 14/04/17.

 motoe2 android token - priya@crajee.com
 ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr

 motog5splus2 android token - abhi@crajee.com
 f1ZopkTtXas:APA91bFZVD3HVC9UHWCfwnp6IbOUQRdTM0TOKjuRy-6yoDDqJW9t1N1bncjRmvnp4qVGbH1Ncf32eOfdC6hImCIeFPgDI8pbQXs3PFnSCTmOgjBdiV_yyuYSUhK9IsRGvhq68ik0LQga


 projectId: united-blend-199107


 */
public class FirebaseServerTest {

    @Test
    public void sendHttpMessage() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + getAccessToken());

        // device token linked to the user
        HttpEntity<?> httpEntity = new HttpEntity<>("{ \"to\" : \"fZ8kyXfSVTM:APA91bHyx5fsye_ZLXFMphJKJ-qH5n_VkwekcOVClFOHse0j2Jpme0COchqv4DcbznfPDVW6E7OQdWURxgFUNsUcNUWc3IMuHWSJ9jyFhTUPYWDgJu0T_fOKRrP5yq4YW5HuTzponFUm\" }", httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity("https://fcm.googleapis.com/fcm/send", httpEntity, Map.class);
        System.out.println(response.getBody());

    }


    public static void main(String[] args) throws FirebaseMessagingException, IOException {
        FirebaseServerTest t = new FirebaseServerTest();
        t.init();
//        t.sendHttpMessage();
        t.sendToToken();
    }

    public void init() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("/Users/a.nigam/Documents/firebase/united-blend-199107-firebase-adminsdk-p06ca-4def35df78.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://united-blend-199107.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }

    /**
     * - Data message is always received in the onMessageReceived of MyFirebaseMessagingService
     * - Notification message is received int he above method only if the app is on the foreground.
     * Foreground app can can show this messaeg in the app notifications. Background app
     * gets the notification in the notification bar
     * -
     * @throws FirebaseMessagingException
     */
    public void sendToToken() throws FirebaseMessagingException {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr";
        FirebaseApp app = FirebaseApp.initializeApp("crajee");
        FirebaseMessaging.getInstance(app);
        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(new Notification("hello abhi - 1","message from code !"))
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        // [END send_to_token]
    }
    private static String SCOPE =
            "https://www.googleapis.com/auth/firebase.messaging";

    public String getAccessToken() throws IOException {


        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream("firebase-admin-key.json"))
                .createScoped(Arrays.asList(SCOPE));
        googleCredential.refreshToken();
        String token = googleCredential.getAccessToken();
        return token;

    }
}
