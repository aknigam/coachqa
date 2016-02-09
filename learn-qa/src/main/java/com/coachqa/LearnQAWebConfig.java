package com.coachqa;


import com.coachqa.mvc.SecurityConfig;
import com.coachqa.notification.ClassroomEventRegistrationProvider;
import com.coachqa.web.interceptor.LearnQARequestInterceptor;
import notification.DefaultRegistrationProvider;
import notification.EventNotificationProcessor;
import notification.impl.DefaultRegsitrationProviderFactory;
import notification.impl.EventNotificationProcessorImpl;
import notification.impl.EventProcessorImpl;
import notification.publisher.AsyncEventQueuePublisher;
import notification.publisher.NotificationPublisher;
import notification.repository.EventDAO;
import notification.repository.impl.DBEventDao;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/*
Look for annotation @AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})
Using this we can define the order in which the configurations files are loaded and used to configure beans.

The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes:

 */
@EnableWebMvc
@EnableCaching
@EnableTransactionManagement()
@Import(SecurityConfig.class)
@Order(1)
@SpringBootApplication
public class LearnQAWebConfig extends WebMvcConfigurerAdapter {

    /**
     * http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc-default-servlet-handler
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LearnQARequestInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.freeMarker().cache(false);
        registry.freeMarker().prefix("");
        registry.freeMarker().suffix(".html");

        /*&

        <property name="cache" value="false"/>
      <property name="prefix" value=""/>
      <property name="suffix" value=".html"/>


         */
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyConfigurer(){
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        Resource[] resources = new Resource[1];
        resources[0] = new ClassPathResource("learnqa.appconfig.properties");
        placeholderConfigurer.setLocations(resources);
        return placeholderConfigurer;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("questions")
                , new ConcurrentMapCache("tags")
                , new ConcurrentMapCache("usersByIdCache")));
        return cacheManager;
    }

    /*
    http://stackoverflow.com/questions/27843788/resource-annotation-no-qualifying-bean-of-type-javax-sql-datasource-is-define
     */
    @Bean
    @Primary
    public DataSource learnqadataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn-qa");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(){
        DataSourceTransactionManager txnManager = new DataSourceTransactionManager();
        txnManager.setDataSource(learnqadataSource());
        return txnManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager txManager){
        return new TransactionTemplate(txManager);
    }

    @Bean
    public JdbcTemplate learnqajdbcTemplate(){
        return new JdbcTemplate(learnqadataSource());
    }
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        return freeMarkerConfigurer;
    }

    // notification system configuration


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
        return new EventNotificationProcessorImpl();
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
        return new EventProcessorImpl(notificationPublisher, eventDao, eventNotificationProcessor, eventRegistrationFactory);
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(LearnQAWebConfig.class, args);
    }
}
