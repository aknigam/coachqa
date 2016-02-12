package com.coachqa.security;

import com.coachqa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
        System.out.println("Init done !!!"+ userService);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        request.getSession().setAttribute("username", username);

        request.getSession().setAttribute("userId",userService.getUserByEmail(username));
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