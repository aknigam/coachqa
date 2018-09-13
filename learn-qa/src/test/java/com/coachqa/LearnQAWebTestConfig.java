package com.coachqa;


import com.coachqa.config.DBConfig;
import com.coachqa.service.impl.UsersNotificationListener;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.SimpleRetryingEventListener;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.NotificationService;
import notification.entity.EventType;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;


/*
Look for annotation @AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})
Using this we can define the order in which the configurations files are loaded and used to configure beans.

The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes:

 */
@EnableWebMvc
@EnableCaching
@EnableTransactionManagement()
@Order(1)
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class LearnQAWebTestConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private YamlProcessor yamlMapFactoryBean;
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

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * This bean disables the {@link AuthenticationManagerConfiguration}
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public AuthenticationManager authenticationManager() throws Exception {
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> c = new JdbcUserDetailsManagerConfigurer<>();
        c.dataSource(learnqadataSource())
                .usersByUsernameQuery("select email , pasword, true as enabled from appuser where email =?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from appuser where email = ?");

        DaoAuthenticationProvider daoAuthenticationProvide = new DaoAuthenticationProvider();
        daoAuthenticationProvide.setUserDetailsService(c.getUserDetailsService());
        return new ProviderManager(Arrays.asList(new AuthenticationProvider[]{daoAuthenticationProvide}));
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

    @Autowired
    private DBConfig dbConfig;

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

    /*
    @Bean
    public SqlSessionFactory sqlSessionFactory(){

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment myBatisEnvironment =
                new org.apache.ibatis.mapping.Environment("dev", transactionFactory, datasource());
        org.apache.ibatis.session.Configuration mybatisConfiguration = new org.apache.ibatis.session.Configuration(myBatisEnvironment);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfiguration);
        mybatisConfiguration.addMappers("com.smartbookmark.repository.mybatis.mapper");
        return sessionFactory;
    }
    */

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

    @Bean
    public EventPublisher getEventPublisher(NotificationService notificationService){
        ApplicationEventListener<Integer> userNotificationListener = new UsersNotificationListener(notificationService);
        SimpleEventPublisher<Object> eventPublisher = new SimpleEventPublisher<>();
        eventPublisher.attachListener(EventType.POST_REJECTED, new SimpleRetryingEventListener( userNotificationListener ));
        eventPublisher.attachListener(EventType.QUESTION_POSTED, new SimpleRetryingEventListener( userNotificationListener ));
        eventPublisher.attachListener(EventType.ANSWER_POSTED, new SimpleRetryingEventListener( userNotificationListener ));

        return  eventPublisher;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(LearnQAWebTestConfig.class, args);
    }
}
