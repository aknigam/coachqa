package com.coachqa.repository.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.coachqa.entity.AndroidToken;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coachqa.entity.AppUser;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.repository.dao.sp.AppUserAddSproc;


@Component
public class AppUserDAO extends BaseDao implements UserDAO, InitializingBean {

	private static Logger LOGGER = LoggerFactory.getLogger(AppUserDAO.class);

	private AppUserAddSproc userAddOrUpdateSproc;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		userAddOrUpdateSproc = new AppUserAddSproc(getDataSource());
	}

	private static String m_userByEmailQuery = "select AppUserId, firstname, email, lastname  from AppUser where Email = ?";
	
	@Override
	public AppUser getUserByEmail(String userEmail) {

		try {
			List<AppUser> users = jdbcTemplate.query(m_userByEmailQuery, new String[]{userEmail}, new RowMapper<AppUser>() {

				@Override
				public AppUser mapRow(ResultSet rs, int i)
						throws SQLException {
					
					AppUser user = new AppUser();
					user.setAppUserId(rs.getInt("AppUserId"));
					user.setEmail(rs.getString("email"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
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


	@CachePut(value="usersByIdCache", key="#result.appUserId")
	@Override
	public AppUser addUser(AppUser user) {
		try {
			return userAddOrUpdateSproc.addUser(user);
		}catch (DuplicateKeyException dke){
			LOGGER.error(String.format( "User with email %s already exists"), dke);
			throw new UserAlreadyExistsException(ApplicationErrorCode.USER_ALREADY_EXISTS, String.format( "User with email %s already exists"));
		}
	}

	private static String m_userByIdQuery = "select AppUserId, firstname, MiddleName, LastName,  email  from AppUser where appUserId = ?";

	@Cacheable(value="usersByIdCache", key="#userId")
	@Override
	public AppUser getUserByIdentifier(Integer userId) {

		List<AppUser> users = jdbcTemplate.query(m_userByIdQuery, new Integer[]{userId}, new RowMapper<AppUser>() {

			@Override
			public AppUser mapRow(ResultSet rs, int i)
					throws SQLException {

				AppUser user = new AppUser();
				user.setAppUserId(rs.getInt("AppUserId"));
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setMiddleName(rs.getString("MiddleName"));
				user.setLastName(rs.getString("LastName"));
				return user;
			}
		});
		if(users.size() == 1)
			return users.get(0);
		else
		{
			throw new UserNotFoundException("User with identifier ["+userId+"] does not exists.");
		}

	}

	private static String m_adminUserQuery = "select AppUserId from AppUser where UserTypeId = 2";

	@Override
	public List<Integer> getPostContentApprovers() {
		return jdbcTemplate.query(m_adminUserQuery, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int i)
					throws SQLException {


				return rs.getInt("AppUserId");

			}
		});

	}

	private static String addAndroidToken = "Insert into AppUserDetail (AppUserId, AndroidToken) values (?, ?)";

	@Override
	public void addAndroidUserToken(AndroidToken androidToken) {
		jdbcTemplate.update(addAndroidToken, new Object[]{androidToken.getAppUserId()
				, androidToken.getAndroidToken()});
	}

	private static String getAddAndroidTokenQuery = "select AndroidToken from AppUser where UserTypeId = 2";
	public List<String> getAndroidTokens(Integer userId){
		return jdbcTemplate.query(m_userByIdQuery, new Integer[]{userId}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int i)
					throws SQLException {

				return rs.getString("AndroidToken");
			}
		});

	}


}
