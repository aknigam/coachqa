package com.coachqa.mvc;


import com.coachqa.web.interceptor.LearnQARequestInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebMvc
@EnableCaching
@EnableTransactionManagement()
@ComponentScan(basePackages = "com.coachqa")
@Import(SecurityConfig.class)
@Order(1)
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
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("questions")));
        return cacheManager;
    }

    @Bean
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


    /*

  <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="learnqadataSource"/>
  </bean>


  <bean id="learnqajdbcTemplate"
     class="org.springframework.jdbc.core.JdbcTemplate">
     <constructor-arg ref="learnqadataSource" />
  </bean>

  <tx:annotation-driven transaction-manager="txManager"/>
     */


}
