package com.coachqa;


import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


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

//                .antMatchers("/swagger-ui.html").authenticated(); // this will make swagger page secured
                .antMatchers("/swagger-ui.html").permitAll(); // this makes swagger page to be unauthenticated



    }



}