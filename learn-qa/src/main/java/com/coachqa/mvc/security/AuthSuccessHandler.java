package com.coachqa.mvc.security;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

    private final ObjectMapper mapper = new ObjectMapper();

    //@Autowired
    //DataSource learnqadataSource;

    @PostConstruct
    public void init(){
        System.out.println("Init done !!!");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        request.getSession().setAttribute("username", username);

        LOGGER.info(userDetails.getUsername() + " got is connected ");

        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, username);
        writer.flush();


    }


    private static String m_userByEmailQuery = "select AppUserId, firstname, email  from AppUser where Email = ?";

    /*
    public AppUser getUserByEmail(String userEmail) {
        JdbcTemplate securityJdbcTemplate = new JdbcTemplate(learnqadataSource);
        try {
            List<AppUser> users = securityJdbcTemplate.query(m_userByEmailQuery, new String[]{userEmail}, new RowMapper<AppUser>() {

                @Override
                public AppUser mapRow(ResultSet rs, int i)
                        throws SQLException {

                    AppUser user = new AppUser();
                    user.setAppUserId(rs.getInt("AppUserId"));
                    return user;
                }
            });
            if(users.size() == 1)
                return users.get(0);
            else
            {
                throw new UserNotFoundException("User with email ["+userEmail+"] does not exists.");
            }

        } catch (DataAccessException e) {
            return null;
        }

    }
    */
}