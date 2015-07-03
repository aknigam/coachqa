package com.coachqa.mvc;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * https://github.com/spring-projects/spring-security/tree/master/samples
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 * http://docs.spring.io/spring-security/site/docs/3.2.x/guides/helloworld.html
 * http://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
 * http://codehustler.org/blog/spring-security-tutorial-form-login-java-config/
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDataSource")
    DataSource learnqadataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    /** http://www.mkyong.com/spring-security/spring-security-form-login-using-database/ */
        auth
                .jdbcAuthentication()
                .dataSource(learnqadataSource)
                .usersByUsernameQuery("select email , pasword, true as enabled from appuser where email =?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from appuser where email = ?");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    public DataSource userDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn-qa");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    /**
     * If following method is not implemented then -
     * Spring Security will automatically render a login page and logout success page for you
     *
     * Refer - http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
     * http://docs.spring.io/spring-security/site/docs/current/guides/html5//form.html
     * http://docs.spring.io/spring-security/site/docs/4.0.1.RELEASE/reference/htmlsingle/#headers-frame-options
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/questions/**").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/users/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/questions/home")
                .and().logout().logoutSuccessUrl("/login?logout").and().csrf().disable().httpBasic()
                .and()
                .headers()
                        .frameOptions()
                        .sameOrigin();

    }



}