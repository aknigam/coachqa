package notification.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Post;
import com.coachqa.notification.ClassroomEventRegistrationProvider;
import com.coachqa.notification.ContentApproverProvider;
import com.coachqa.notification.PostEventInterestedUsersProvider;
import com.coachqa.service.ClassroomService;
import com.coachqa.service.PostService;
import com.coachqa.service.UserService;
import notification.EventRegisteredUsersProvider;
import notification.NotificationService;
import notification.SendEventNotificationProcessor;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * This is more useful in learn-qa project
 */
@Configuration
public class TestConfig {


    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/notificationsystem");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }



    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public EventDao eventDAO(JdbcTemplate jdbcTemplate, DataSource dataSource){
        return new DBEventDao(jdbcTemplate, dataSource);
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
        return new EventNotificationConsumer(eventDAO, userEventNotificationDao, null);
    }

    @Bean
    public NotificationPublisher notificationPublisher(SendEventNotificationProcessor eventNotificationProcessor){
        return new AsyncEventQueuePublisher(eventNotificationProcessor);
    }


    @Bean
    public DefaultRegsitrationProviderFactory eventRegistrationFactory(){
        Map<EventType, EventRegisteredUsersProvider> defaultRegistrationProviderMap = new HashMap<>();

        ClassroomService classroomService = Mockito.mock(ClassroomService.class);
        defaultRegistrationProviderMap.put(EventType.MEMBERSHIP_REQUEST, new ClassroomEventRegistrationProvider(classroomService));


        PostService postService = Mockito.mock(PostService.class);
        Post post = Mockito.mock(Post.class);
        Mockito.when(post.getContent()).thenReturn("post content");
        AppUser appUser= new AppUser(2, "anigam@gmail.com", "firstName", "middleName", "lastName");
        Mockito.when(post.getPostedBy()).thenReturn(appUser);

        Mockito.when(postService.getPostById(Mockito.anyInt())).thenReturn(post);

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.getPostContentApprovers()).thenReturn(Arrays.asList(new Integer[]{2}));
        PostEventInterestedUsersProvider postEventInterestedUsersProvider = new PostEventInterestedUsersProvider(postService, classroomService, userService);

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
        return new NotificationServiceImpl(eventDao, eventNotificationProcessor, eventRegistrationFactory, eventRegistrationDao,
                userEventNotificationDao, userNotificationPreferenceDao);
    }

}