package notification.impl;

import notification.NotificationService;
import notification.SendEventNotificationProcessor;
import notification.entity.*;
import notification.enums.EventNotificationStatus;
import notification.enums.NotificationTypeEnum;
import notification.repository.EventDao;
import notification.repository.EventRegistrationDao;
import notification.repository.UserEventNotificationDao;
import notification.repository.UserNotificationPreferenceDao;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.web.WebAppConfiguration;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by a.nigam on 11/04/17.
 */
@WebAppConfiguration
public class NotificationServiceTest extends TestConfig{

    /*
    EventRegistrationDao eventRegistrationDao,
                                   UserEventNotificationDao userEventNotificationDao,
                                   UserNotificationPreferenceDao userNotificationPreferenceDao){
     */
    @Test
    public void testNotification(){
        DataSource dataSource = dataSource();
        JdbcTemplate jdbcTemplate = jdbcTemplate(dataSource);
//        EventDao eventDao = eventDAO(jdbcTemplate, dataSource);
        ApplicationEvent event = new ApplicationEvent(EventType.ANSWER_POSTED, 1, EventStage.STAGE_ONE);
        EventDao eventDao = Mockito.mock(EventDao.class);
        Mockito.doNothing().when(eventDao).createEvent(event);
        Mockito.doNothing().when(eventDao).updateEventDate(event);

        EventRegistrationDao eventRegistrationDao = Mockito.mock(EventRegistrationDao.class);
        Mockito.when(eventRegistrationDao.getRegisteredUsers(Mockito.any())).thenReturn(new ArrayList<Integer>());

        UserEventNotificationDao userEventNotificationDao = Mockito.mock(UserEventNotificationDao.class);

        Integer user = 2;
        UserEventNotification nu = new UserEventNotification(EventNotificationStatus.NEW, NotificationTypeEnum.EMAIL, event, user);
        Mockito.when(userEventNotificationDao.persistUserEventNotification(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(nu);
        UserNotificationPreferenceDao userNotificationPreferenceDao = Mockito.mock(UserNotificationPreferenceDao.class);
        List<NotificationPreference> prefs = new ArrayList<>();
        prefs.add(new NotificationPreference(1, NotificationTypeEnum.EMAIL));
        Mockito.when(userNotificationPreferenceDao.getNotificationPreference(Mockito.any())).thenReturn(prefs);

        SendEventNotificationProcessor eventNotificationProcessor = eventNotificationProcessor(eventDao, userEventNotificationDao);
        DefaultRegsitrationProviderFactory defaultResgistrationProviderFactory = eventRegistrationFactory();

        NotificationService service = new NotificationServiceImpl(eventDao, eventNotificationProcessor, defaultResgistrationProviderFactory,
                eventRegistrationDao, userEventNotificationDao, userNotificationPreferenceDao);


        service.notifyUsers(event);


    }


}
