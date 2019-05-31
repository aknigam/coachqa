package com.coachqa.repository.dao.impl;

import com.coachqa.entity.Account;
import com.coachqa.entity.AndroidToken;
import com.coachqa.entity.AppUser;
import com.coachqa.exception.ApplicationErrorCode;
import com.coachqa.exception.UserAlreadyExistsException;
import com.coachqa.exception.UserNotFoundException;
import com.coachqa.repository.dao.UserDAO;
import com.coachqa.repository.dao.mybatis.mapper.AppUserMapper;
import com.coachqa.repository.dao.sp.AppUserAddSproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class AppUserDAO extends BaseDao implements UserDAO, InitializingBean {

	private static Logger LOGGER = LoggerFactory.getLogger(AppUserDAO.class);

	private AppUserAddSproc userAddOrUpdateSproc;

	private static String m_userByIdQuery = "select appuserid, firstname, middlename, lastname,  email  from appuser where appUserId = ?";

	private static String m_userByEmailQuery = "select appuserid, firstname, email, lastname, accountId  from appuser" +
			" where " +
			"email = ?";

	private static String m_adminUserQuery = "select appuserid from appuser where usertypeid = 2";

	private static String addAndroidToken = "Insert into appuserdetail (appuserid, androidtoken) values (?, ?)";

	private static String getAddAndroidTokenQuery = "select androidtoken from appuser where usertypeid = 2";

	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		userAddOrUpdateSproc = new AppUserAddSproc(getDataSource());
	}


	
	@Override
	public AppUser getUserByEmail(String userEmail) {

		return appUserMapper.getUserByEmail(userEmail);
		
	}


	@CachePut(value="usersByIdCache", key="#result.appUserId")
	@Override
	public AppUser addUser(AppUser user) {
		try {
            appUserMapper.addUer(user);
//            user.setAppUserId(userId);
            return user;
//			return userAddOrUpdateSproc.addUser(user);
		} catch (DuplicateKeyException dke){
			LOGGER.error(String.format( "User with email %s already exists", user.getEmail()), dke);
			throw new UserAlreadyExistsException(ApplicationErrorCode.USER_ALREADY_EXISTS, String.format( "User with email %s already exists", user.getEmail()));
		}
	}



	@Cacheable(value="usersByIdCache", key="#userId")
	@Override
	public AppUser getUserByIdentifier(Integer userId) {
		return  appUserMapper.getUserById(userId);
	}



	@Override
	public List<Integer> getPublicPostContentApprovers() {



		return jdbcTemplate.query(m_adminUserQuery, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int i)
					throws SQLException {


				return rs.getInt("appuserid");

			}
		});

	}



	@Override
	public void addAndroidUserToken(AndroidToken androidToken) {
		jdbcTemplate.update(addAndroidToken, new Object[]{androidToken.getAppUserId()
				, androidToken.getAndroidToken()});
	}


	public List<String> getAndroidTokens(Integer userId){
		return jdbcTemplate.query(m_userByIdQuery, new Integer[]{userId}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int i)
					throws SQLException {

				return rs.getString("androidtoken");
			}
		});

	}

	@Override
	public void addAndroidToken(String androidToken, Integer appUserId) {
		appUserMapper.addAndroidToken(androidToken, appUserId);
	}


}
