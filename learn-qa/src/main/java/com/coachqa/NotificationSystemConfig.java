package com.coachqa;


import com.coachqa.notification.ClassroomEventRegistrationProvider;
import notification.DefaultRegistrationProvider;
import notification.EventNotificationProcessor;
import notification.entity.ApplicationEvent;
import notification.impl.DefaultRegsitrationProviderFactory;
import notification.impl.EventNotificationConsumer;
import notification.impl.NotificationServiceImpl;
import notification.publisher.AsyncEventQueuePublisher;
import notification.publisher.NotificationPublisher;
import notification.repository.EventDAO;
import notification.repository.impl.DBEventDao;
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
    public EventDAO eventDAO(JdbcTemplate jdbcTemplate, DataSource notificationDataSource){
        return new DBEventDao(jdbcTemplate, notificationDataSource);
    }

    @Bean
    public EventNotificationProcessor eventNotificationProcessor(){
        return new EventNotificationConsumer() {
            @Override
            protected String getNotificationMessage(ApplicationEvent eventInstance) {
                return "Dummy message";
            }
        };
    }

    @Bean
    public NotificationPublisher notificationPublisher(EventNotificationProcessor eventNotificationProcessor){
        return new AsyncEventQueuePublisher(eventNotificationProcessor);
    }

    @Bean
    public DefaultRegsitrationProviderFactory eventRegistrationFactory(){
        Map<String, DefaultRegistrationProvider> defaultRegistrationProviderMap = new HashMap<>();
        defaultRegistrationProviderMap.put("MEMBERSHIP_REQUEST", new ClassroomEventRegistrationProvider());
        return new DefaultRegsitrationProviderFactory(defaultRegistrationProviderMap);
    }

    @Bean
    public notification.NotificationService notificationService(NotificationPublisher notificationPublisher, EventDAO eventDao
            , EventNotificationProcessor eventNotificationProcessor, DefaultRegsitrationProviderFactory eventRegistrationFactory){
        return new NotificationServiceImpl(notificationPublisher, eventDao, eventNotificationProcessor, eventRegistrationFactory);
    }
}
