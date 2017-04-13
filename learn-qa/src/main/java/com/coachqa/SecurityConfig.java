package com.coachqa;



import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * https://github.com/spring-projects/spring-security/tree/master/samples
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 * http://docs.spring.io/spring-security/site/docs/3.2.x/guides/helloworld.html
 * http://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
 * http://codehustler.org/blog/spring-security-tutorial-form-login-java-config/
 *
 * Oauth2 references:
 *  - https://github.com/spring-projects/spring-security-oauth/tree/master/spring-security-oauth2/
 *  - http://www.appsdev.is.ed.ac.uk/blog/?p=736
 *
 *  - https://github.com/spring-projects/spring-security-oauth/tree/master/spring-security-oauth2/
 *  - http://www.appsdev.is.ed.ac.uk/blog/?p=736
 *
 * Take a look at to understand how to secure rest web services-
 *
 *  https://dzone.com/articles/secure-rest-services-using
 *
 */
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = "com.coachqa.security")
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /** http://www.mkyong.com/spring-security/spring-security-form-login-using-database/ */
        auth
                .jdbcAuthentication()
                .dataSource(userDataSource())
                .usersByUsernameQuery("select email , pasword, true as enabled from appuser where email =?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from appuser where email = ?");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/swagger-ui.html");
    }

//    // @Bean
//    public AuthSuccessHandler authSuccessHandler(){
//        return new AuthSuccessHandler();
//    }

    @Bean
    public DataSource userDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn-qa");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }


//    // @Bean
//    public UserDetailsService userDetailsService(){
//        return null;
//    }

//    // @Bean
//    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        //authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
//
//        return authenticationProvider;
//    }

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
//                .antMatchers("/api/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated();


    }



}