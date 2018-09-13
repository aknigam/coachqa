package com.coachqa;

import com.coachqa.config.DBConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by a.nigam on 06/01/17.
 *
 * https://github.com/spring-projects/spring-security-oauth/tree/master/spring-security-oauth2/
 * http://www.appsdev.is.ed.ac.uk/blog/?p=736
 *
 */
//@Configuration
//@EnableAuthorizationServer
//@EnableConfigurationProperties(DBConfig.class)
//@Order(3)
public class AuthorisationServerConfiguration extends OAuth2AuthorizationServerConfiguration {


    public AuthorisationServerConfiguration(BaseClientDetails details,
                                            AuthenticationManager authenticationManager,
                                            ObjectProvider<TokenStore> tokenStoreProvider,
                                            AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStoreProvider, properties);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);

        endpoints.authenticationManager(authenticationManager());

    }

    @Autowired
    DBConfig dbConfig;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        return new ProviderManager(Arrays.asList(authProvider()));

    }

    @Bean
    public AuthenticationProvider authProvider() throws Exception {
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> c = new JdbcUserDetailsManagerConfigurer<>();
        c.dataSource(datasource(dbConfig))
                .usersByUsernameQuery("select email , pasword, true as enabled from appuser where email =?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from appuser where email = ?");

        DaoAuthenticationProvider daoAuthenticationProvide = new DaoAuthenticationProvider();
        daoAuthenticationProvide.setUserDetailsService(c.getUserDetailsService());
        return  daoAuthenticationProvide;

    }
    @Bean
    @Primary
    public DataSource datasource(DBConfig dbConfig){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbConfig.getDriver());
        dataSource.setUrl(dbConfig.getUrl());
        dataSource.setUsername(dbConfig.getUsername());
        dataSource.setPassword(dbConfig.getPassword());
        return dataSource;
    }



}
