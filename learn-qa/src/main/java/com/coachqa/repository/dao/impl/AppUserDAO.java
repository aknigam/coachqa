package com.coachqa.repository.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.repository.dao.sp.AppUserAddSproc;
import com.coachqa.ws.model.UserModel;

@Component
public class AppUserDAO extends BaseDao implements UserDAO, InitializingBean {

//	@Autowired
//	@Qualifier("learnqadataSource")
//	private DataSource dataSource;
	
	private AppUserAddSproc userAddOrUpdateSproc;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		userAddOrUpdateSproc = new AppUserAddSproc(getDataSource());
	}

	private static String m_userByEmailQuery = "select AppUserId, firstname, email  from AppUser where Email = ?";
	
	@Override
	public AppUser getUserByEmail(String userEmail) {

		try {
			List<AppUser> users = jdbcTemplate.query(m_userByEmailQuery, new String[]{userEmail}, new RowMapper<AppUser>() {

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

	
	@Override
	public AppUser addUser(UserModel user) {
		return userAddOrUpdateSproc.addUser(user);
	}


	@Override
	public AppUser getUserByIdentifier(Integer userId) {
		return userAddOrUpdateSproc.getUserByIdentifier(userId);
	}
	

}
