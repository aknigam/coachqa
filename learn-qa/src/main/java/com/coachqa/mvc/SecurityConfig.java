package com.coachqa.mvc;


import com.coachqa.mvc.security.AuthFailureHandler;
import com.coachqa.mvc.security.AuthSuccessHandler;
import com.coachqa.mvc.security.HttpAuthenticationEntryPoint;
import com.coachqa.mvc.security.HttpLogoutSuccessHandler;
import com.coachqa.service.UserService;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * https://github.com/spring-projects/spring-security/tree/master/samples
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 * http://docs.spring.io/spring-security/site/docs/3.2.x/guides/helloworld.html
 * http://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
 * http://codehustler.org/blog/spring-security-tutorial-form-login-java-config/
 *
 * Take a look at to understand how to secure rest web services-
 *
 *  https://dzone.com/articles/secure-rest-services-using
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.coachqa.mvc.security")
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGIN_PATH = "/authenticate";


    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;


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

    // @Bean
    public AuthSuccessHandler authSuccessHandler(){
        return new AuthSuccessHandler();
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


    // @Bean
    public UserDetailsService userDetailsService(){
        return null;
    }

    // @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        //authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

        return authenticationProvider;
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

        http.csrf().disable()
                // .authenticationProvider(authenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .permitAll()
                .loginProcessingUrl(LOGIN_PATH)
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1);

        http.authorizeRequests().anyRequest().authenticated();


    }



}