package com.coachqa;


import com.coachqa.notification.ClassroomEventRegistrationProvider;
import com.coachqa.notification.ContentApproverProvider;
import com.coachqa.notification.PostApprovedInterestedUsersProvider;
import com.coachqa.notification.QuestionOwnerRegistrationProvider;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;

import notification.EventRegisteredUsersProvider;
import notification.NotificationService;
import notification.SendEventNotificationProcessor;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import notification.impl.DefaultRegsitrationProviderFactory;
import notification.impl.EventNotificationConsumer;
import notification.impl.NotificationServiceImpl;
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
    public SendEventNotificationProcessor eventNotificationProcessor(EventDao eventDAO, UserEventNotificationDao userEventNotificationDao){
        return new EventNotificationConsumer(eventDAO, userEventNotificationDao) {
            @Override
            protected String getNotificationMessage(ApplicationEvent eventInstance) {
                return "dummy message "+ eventInstance.getEventSource();
            }
        };
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

        QuestionOwnerRegistrationProvider questionOnwerRegistrationProvider = new QuestionOwnerRegistrationProvider(questionService);
        PostApprovedInterestedUsersProvider approvedInterestedUsersProvider = new PostApprovedInterestedUsersProvider(postService, classroomService);
        PostApprovedInterestedUsersProvider rejectedPostInterestedUsersProvider = new PostApprovedInterestedUsersProvider(postService, classroomService);

        defaultRegistrationProviderMap.put(EventType.POST_APPROVED, approvedInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_REJECTED, questionOnwerRegistrationProvider);

        defaultRegistrationProviderMap.put(EventType.QUESTION_ANSWERED, questionOnwerRegistrationProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_DELETED, questionOnwerRegistrationProvider); // not yet approved
//
        defaultRegistrationProviderMap.put(EventType.QUESTION_UPDATED, approvedInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_VIEWED, questionOnwerRegistrationProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_VOTED, questionOnwerRegistrationProvider);




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
