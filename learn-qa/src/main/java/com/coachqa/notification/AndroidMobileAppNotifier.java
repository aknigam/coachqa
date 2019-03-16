package com.coachqa.notification;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.service.impl.QuestionServiceImpl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import notification.EventNotifier;
import notification.NotifierFactory;
import notification.enums.NotificationTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Notifier factory should be able to pick this implementation
 */
@Component
public class AndroidMobileAppNotifier implements EventNotifier {

    private static Logger LOGGER = LoggerFactory.getLogger(AndroidMobileAppNotifier.class);

    private static final String APP_NAME = "crajee";
    private static final String PATH_SERVICE_ACCOUNT_KEY = "/Users/a.nigam/Documents/firebase/united-blend-199107-firebase-adminsdk-p06ca-4def35df78.json";


    private static final String defaultRegistrationToken =
            "ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr";

    @Autowired
    private NotifierFactory notifierFactory;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream(PATH_SERVICE_ACCOUNT_KEY);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://united-blend-199107.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        notifierFactory.register(NotificationTypeEnum.APP, this);

    }

    @Override
    public void sendNotification(String notificationMessage, int userId) {
        try {
            AppUser appUser = userService.getUserDetails(userId);
            String registrationToken = appUser.getAndroidToken();
            // TODO: 16/03/19 following line should be removed after initial testing
            registrationToken = registrationToken == null ? defaultRegistrationToken : registrationToken;

            FirebaseApp app = FirebaseApp.initializeApp(APP_NAME);
            FirebaseMessaging.getInstance(app);
            // See documentation on defining a message payload.
            Message message = Message.builder()
                    .setNotification(new Notification("hello", notificationMessage))
//                .putData("score", "850")
//                .putData("time", "2:45")
                    .setToken(registrationToken)
                    .build();

            // Send a message to the device corresponding to the provided
            // registration token.
            String response =  FirebaseMessaging.getInstance().send(message);
            LOGGER.debug("Successfully sent android notification: " + response);

        } catch (FirebaseMessagingException e) {
            LOGGER.error("Unexpected error occurred while sending android notification", e);
        }

    }
}
