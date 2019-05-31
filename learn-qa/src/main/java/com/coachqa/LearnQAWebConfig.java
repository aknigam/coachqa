package com.coachqa;


import com.coachqa.config.DBConfig;
import com.coachqa.service.impl.UsersNotificationListener;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.coachqa.service.listeners.SimpleRetryingEventListener;
import com.coachqa.service.listeners.question.EventConsumer;
import com.coachqa.service.listeners.question.EventPublisher;
import com.coachqa.service.listeners.question.ImageToTextOcrListener;
import com.coachqa.service.listeners.question.SimpleEventConsumer;
import com.coachqa.service.listeners.question.SimpleEventPublisher;
import notification.NotificationService;
import notification.entity.ApplicationEvent;
import notification.entity.EventType;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/*
Look for annotation @AutoConfigureAfter(value = {MetricsConfiguration.class, DatabaseConfiguration.class})
Using this we can define the order in which the configurations files are loaded and used to configure beans.

The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and
@ComponentScan with their default attributes:

To work with @ConfigurationProperties beans you can just inject them in the same way as any other bean.


todo : This service should only return the error code to the clients and not the exact error message.
todo: This way we can hide the system specific error and still give the ability to the client to debug if somethign doesn't
todo: works as expected.

 */
@EnableWebMvc
@EnableCaching
@EnableTransactionManagement()
// @Import(SecurityConfig.class)
@Import({ResourceOAuthSecurityConfiguration.class})
@EnableConfigurationProperties(DBConfig.class)
@Order(1)
//@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
@EnableAuthorizationServer
@EnableAutoConfiguration(exclude = {SolrAutoConfiguration.class})
@MapperScan("com.coachqa.repository.dao.mybatis.mapper")
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
    public AuthenticationManager authenticationManager(DBConfig dbConfig) throws Exception {
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> c = new JdbcUserDetailsManagerConfigurer<>();
        c.dataSource(learnqadataSource(dbConfig))
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



    @Bean
    @Primary
    public DataSource learnqadataSource(DBConfig dbConfig){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUsername());
        dataSource.setPassword(dbConfig.getPassword());
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
    public PlatformTransactionManager txManager(DBConfig dbConfig){
        DataSourceTransactionManager txnManager = new DataSourceTransactionManager();
        txnManager.setDataSource(learnqadataSource(dbConfig));
        return txnManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager txManager){
        return new TransactionTemplate(txManager);
    }

    @Bean
    public JdbcTemplate learnqajdbcTemplate(DBConfig dbConfig){
        return new JdbcTemplate(learnqadataSource(dbConfig));
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

    /**
     * Better approach: instead of injecting the queue through spring DI. Publisher and consumer can also take it
     * from a factory.
     * @return
     */
    @Bean
    public BlockingQueue queue(){
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public EventPublisher getEventPublisher(BlockingQueue queue) {
        return new SimpleEventPublisher(queue);
    }

//    @Autowired
//    private ImageToTextOcrListener ocrListener;

    @Bean
    public EventConsumer getEventConsumer(NotificationService notificationService, BlockingQueue queue, ImageToTextOcrListener ocrListener) {
        BlockingQueue<ApplicationEvent> questionUpdatesQueue = new LinkedBlockingQueue<>();
        ApplicationEventListener userNotificationListener = new UsersNotificationListener(notificationService);


        EventConsumer eventConsumer = new SimpleEventConsumer(queue);

        eventConsumer.attachListener(EventType.POST_REJECTED, new SimpleRetryingEventListener( userNotificationListener ));
        eventConsumer.attachListener(EventType.MEMBERSHIP_REQUEST, new SimpleRetryingEventListener(userNotificationListener));

        eventConsumer.attachListener(EventType.QUESTION_POSTED, new SimpleRetryingEventListener( userNotificationListener ));
        eventConsumer.attachListener(EventType.QUESTION_POSTED, new SimpleRetryingEventListener( ocrListener));
        eventConsumer.attachListener(EventType.POST_APPROVED, new SimpleRetryingEventListener( ocrListener));
        eventConsumer.attachListener(EventType.POST_APPROVED, new SimpleRetryingEventListener( userNotificationListener));
        eventConsumer.attachListener(EventType.QUESTION_ANSWERED, new SimpleRetryingEventListener( userNotificationListener ));
        eventConsumer.attachListener(EventType.QUESTION_ANSWERED, new SimpleRetryingEventListener( ocrListener));

        eventConsumer.setDefaultListener(new SimpleRetryingEventListener( userNotificationListener ));
        return  eventConsumer;
    }
    /*
    Added to make swagger ui work with spring boot oauth-2
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.coachqa.ws.controllor"))
//                .paths(regex("/api*"))
                .build();
    }

// https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html#howto-customize-the-jackson-objectmapper
//    @Bean
//    @Order(100)
//    public Jackson2ObjectMapperBuilderCustomizer configureCustomDateFormat(){
//        return  new ObjectMapperCustomiser();
//    }


    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(LearnQAWebConfig.class);
        app.run(args);

    }


}
