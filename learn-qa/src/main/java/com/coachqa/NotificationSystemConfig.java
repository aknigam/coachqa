package com.coachqa;


import com.coachqa.notification.ClassroomEventRegistrationProvider;
import com.coachqa.notification.ContentApproverProvider;
import com.coachqa.notification.PostEventInterestedUsersProvider;
import com.coachqa.service.*;

import notification.EventRegisteredUsersProvider;
import notification.NotificationService;
import notification.NotifierFactory;
import notification.SendEventNotificationProcessor;
import notification.entity.EventType;
import notification.impl.DefaultRegsitrationProviderFactory;
import notification.impl.EventNotificationConsumer;
import notification.impl.NotificationServiceImpl;
import notification.impl.UserDetailService;
import notification.publisher.AsyncEventQueuePublisher;
import notification.publisher.NotificationPublisher;
import notification.repository.EventDao;
import notification.repository.EventRegistrationDao;
import notification.repository.UserEventNotificationDao;
import notification.repository.UserNotificationPreferenceDao;
import notification.repository.impl.DBEventDao;
import notification.repository.impl.EventRegistrationDaoImpl;
import notification.repository.impl.UserEventNotificationDaoImpl;
import notification.repository.impl.UserNotificationPreferenceDaoImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/*
Look for annotation @AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})
Using this we can define the order in which the configurations files are loaded and used to configure beans.

The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes:

 */


@Configuration
public class NotificationSystemConfig  {

    @Bean
    public DataSource notificationDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/notificationsystem");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource notificationDataSource){
        return new JdbcTemplate(notificationDataSource);
    }

    @Bean
    public EventDao eventDAO(){
        DataSource ds = notificationDataSource();
        return new DBEventDao(jdbcTemplate(ds), ds);
    }

    @Bean
    public UserEventNotificationDao userEventNotificationDao(JdbcTemplate jdbcTemplate, DataSource dataSource){
        return new UserEventNotificationDaoImpl(jdbcTemplate, dataSource);
    }

    @Bean
    public UserNotificationPreferenceDao userNotificationPreferenceDao(JdbcTemplate jdbcTemplate, DataSource dataSource){
        return new UserNotificationPreferenceDaoImpl(jdbcTemplate, dataSource);
    }

    @Bean
    public EventRegistrationDao eventRegistrationDao(JdbcTemplate jdbcTemplate, DataSource dataSource){
        return new EventRegistrationDaoImpl(jdbcTemplate, dataSource);
    }

    @Bean
    public NotifierFactory notifierFactory(UserService userService){
        return new NotifierFactory(userService);
    }

    @Bean
    public SendEventNotificationProcessor eventNotificationProcessor(EventDao eventDAO, UserEventNotificationDao userEventNotificationDao, NotifierFactory notifierFactory){
        return new EventNotificationConsumer(eventDAO, userEventNotificationDao, notifierFactory);
    }


    @Bean
    public NotificationPublisher notificationPublisher(SendEventNotificationProcessor eventNotificationProcessor){
        return new AsyncEventQueuePublisher(eventNotificationProcessor);
    }

    /**
     * We need to support following scenarios:
     *
     * - Any question posted to the class -> all the class members should be notified
     * @param questionService
     * @param classroomService
     * @param postService
     * @return
     */
    @Bean
    public DefaultRegsitrationProviderFactory eventRegistrationFactory(QuestionService questionService, ClassroomService classroomService, PostService postService,
                                                                       UserService UserService){
        Map<EventType, EventRegisteredUsersProvider> defaultRegistrationProviderMap = new HashMap<>();

        defaultRegistrationProviderMap.put(EventType.MEMBERSHIP_REQUEST, new ClassroomEventRegistrationProvider(classroomService));

        EventRegisteredUsersProvider contentApprover = new ContentApproverProvider(UserService);

        defaultRegistrationProviderMap.put(EventType.QUESTION_POSTED, contentApprover); // not yet approved
        defaultRegistrationProviderMap.put(EventType.ANSWER_POSTED, contentApprover);// not yet approved
        // TODO: 10/04/17 handle rejected post event


        PostEventInterestedUsersProvider postEventInterestedUsersProvider = new PostEventInterestedUsersProvider(postService, classroomService, UserService);

        defaultRegistrationProviderMap.put(EventType.QUESTION_POSTED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.ANSWER_POSTED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_DELETED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_UPDATED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_VIEWED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_VOTED, postEventInterestedUsersProvider);




        return new DefaultRegsitrationProviderFactory(defaultRegistrationProviderMap);
    }

    @Bean
    public NotificationService notificationService(NotificationPublisher notificationPublisher, EventDao eventDao,
                                                   SendEventNotificationProcessor eventNotificationProcessor, DefaultRegsitrationProviderFactory eventRegistrationFactory,
                                                   EventRegistrationDao eventRegistrationDao,
                                                   UserEventNotificationDao userEventNotificationDao,
                                                   UserNotificationPreferenceDao userNotificationPreferenceDao){
        return new NotificationServiceImpl( eventDao, eventNotificationProcessor, eventRegistrationFactory, eventRegistrationDao,
                userEventNotificationDao, userNotificationPreferenceDao);
    }
}
