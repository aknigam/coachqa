package com.coachqa;


import com.coachqa.notification.ClassroomEventRegistrationProvider;
import com.coachqa.notification.PostEventInterestedUsersProvider;
import com.coachqa.service.ApprovalProcessor;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.QuestionService;
import com.coachqa.service.UserService;
import com.coachqa.service.impl.ClassroomEventApprovalProcessor;
import com.coachqa.service.impl.PostApprovalProcessor;
import com.coachqa.ws.controllor.ApprovalProcessorFactory;
import notification.EventRegisteredUsersProvider;
import notification.NotificationService;
import notification.NotifierFactory;
import notification.SendEventNotificationProcessor;
import notification.entity.EventType;
import notification.impl.DefaultRegsitrationProviderFactory;
import notification.impl.EventNotificationConsumer;
import notification.impl.NotificationServiceImpl;
import notification.publisher.AsyncEventQueuePublisher;
import notification.publisher.NotificationPublisher;
import notification.repository.EventDAO;
import notification.repository.EventRegistrationDao;
import notification.repository.UserEventNotificationDAO;
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
    public EventDAO eventDAO(){
        DataSource ds = notificationDataSource();
        return new DBEventDao(jdbcTemplate(ds), ds);
    }

    @Bean
    public UserEventNotificationDAO userEventNotificationDao(JdbcTemplate jdbcTemplate, DataSource dataSource){
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
    public NotifierFactory notifierFactory(){
        return new NotifierFactory();
    }

    @Bean
    public SendEventNotificationProcessor eventNotificationProcessor(EventDAO eventDAO, UserEventNotificationDAO userEventNotificationDao, NotifierFactory notifierFactory){
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
        defaultRegistrationProviderMap.put(EventType.MEMBERSHIP_APPROVED, new ClassroomEventRegistrationProvider(classroomService));

//        EventRegisteredUsersProvider contentApprover = new ContentApproverProvider(UserService);

//        defaultRegistrationProviderMap.put(EventType.QUESTION_POSTED, contentApprover); // not yet approved
//        defaultRegistrationProviderMap.put(EventType.ANSWER_POSTED, contentApprover);// not yet approved
        // TODO: 10/04/17 handle rejected post event


        EventRegisteredUsersProvider postEventInterestedUsersProvider = new PostEventInterestedUsersProvider(postService, classroomService, UserService);

        defaultRegistrationProviderMap.put(EventType.QUESTION_POSTED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.QUESTION_ANSWERED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_DELETED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_UPDATED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_VIEWED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_VOTED, postEventInterestedUsersProvider);
        defaultRegistrationProviderMap.put(EventType.POST_APPROVED, postEventInterestedUsersProvider);


        return new DefaultRegsitrationProviderFactory(defaultRegistrationProviderMap);
    }

    @Bean
    public NotificationService notificationService(NotificationPublisher notificationPublisher, EventDAO eventDao,
                                                   SendEventNotificationProcessor eventNotificationProcessor,
                                                   DefaultRegsitrationProviderFactory eventRegistrationFactory,
                                                   EventRegistrationDao eventRegistrationDao,
                                                   UserEventNotificationDAO userEventNotificationDao,
                                                   UserNotificationPreferenceDao userNotificationPreferenceDao){
        return new NotificationServiceImpl( eventDao, eventNotificationProcessor, eventRegistrationFactory, eventRegistrationDao,
                userEventNotificationDao, userNotificationPreferenceDao);
    }

    @Bean
    public PostApprovalProcessor postApprovalProcessor(){
        return new PostApprovalProcessor();
    }

    @Bean
    public ClassroomEventApprovalProcessor classroomEventApprovalProcessor(){
        return new ClassroomEventApprovalProcessor();
    }
    @Bean
    public ApprovalProcessorFactory postApprovalProcessorFactory(ApprovalProcessor postApprovalProcessor,
                                                                 ClassroomEventApprovalProcessor classroomEventApprovalProcessor){

        ApprovalProcessorFactory factory = new ApprovalProcessorFactory();
//        ApprovalProcessor postApprovalProcessor = new PostApprovalProcessor();
        factory.register(EventType.QUESTION_POSTED, postApprovalProcessor);
        factory.register(EventType.QUESTION_ANSWERED, postApprovalProcessor);
//        factory.register(EventType.ANSWER_POSTED, postApprovalProcessor);

//        ClassroomEventApprovalProcessor classroomEventApprovalProcessor = new ClassroomEventApprovalProcessor();
        factory.register(EventType.MEMBERSHIP_REQUEST, classroomEventApprovalProcessor);

        return factory;
    }

}
