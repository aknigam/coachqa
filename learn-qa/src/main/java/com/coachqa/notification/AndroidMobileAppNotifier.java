package com.coachqa.notification;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
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
import java.util.List;

/**
 * Notifier factory should be able to pick this implementation
 */
@Component
public class AndroidMobileAppNotifier implements EventNotifier {

    private static Logger LOGGER = LoggerFactory.getLogger(AndroidMobileAppNotifier.class);

    private static final String APP_NAME = "crajee";
    private static final String PATH_SERVICE_ACCOUNT_KEY = "/Users/a.nigam/Documents/firebase/united-blend-199107-firebase-adminsdk-p06ca-4def35df78.json";

    @Autowired
    private NotifierFactory notifierFactory;

    @Autowired
    private UserService userService;
    private FirebaseApp app;

    @PostConstruct
    public void init() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream(PATH_SERVICE_ACCOUNT_KEY);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://united-blend-199107.firebaseio.com")
                .build();

        app = FirebaseApp.initializeApp(options, APP_NAME);
        notifierFactory.register(NotificationTypeEnum.APP, this);

    }

    @Override
    public void sendNotification(String notificationMessage, int userId) {

        AppUser appUser = userService.getUserDetails(userId);
        List<String> registrationTokens = appUser.getAndroidTokens();

        LOGGER.debug("Sending android notification to {} \n. No of anndroid tokens {}. \nMessage: {}", appUser.getEmail
                        (), registrationTokens.size(),
                notificationMessage);

        for (String registrationToken : registrationTokens) {

            try {

                FirebaseMessaging instance = FirebaseMessaging.getInstance(app);
                // See documentation on defining a message payload.
                Message message = Message.builder()
                        .setNotification(new Notification("App notification ", notificationMessage))
//                        .putData("score", "850")
//                        .putData("time", "2:45")
                        .setToken(registrationToken)
                        .build();

                // Send a message to the device corresponding to the provided
                // registration token.
                String response = instance.send(message);
                LOGGER.debug("Successfully sent android notification: " + response);
            } catch (FirebaseMessagingException e) {
                LOGGER.error("Unexpected error occurred while sending android notification", e);
            }

        }


    }
}
