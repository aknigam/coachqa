package com.coachqa.repository.dao.sp;

import com.coachqa.entity.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;


public class AppUserAddSproc
{
	private SimpleJdbcCall addOrUpdateUserSproc;

	private static String P_MAIL		 = "pEmail";
	private static String P_ASWORD		 = "pPasword";
	private static String P_IRSTNAME		 = "pFirstName";
	private static String P_MIDDLENAME		 = "pMiddleName";
	private static String P_ASTNAME		 = "pLastName";
	


	public AppUserAddSproc(DataSource dataSource)
	{
		addOrUpdateUserSproc = new SimpleJdbcCall(dataSource)
		.withProcedureName("userAddSproc")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_MAIL,
			P_ASWORD,
			P_IRSTNAME,
			P_MIDDLENAME,
			P_ASTNAME
		)
		.declareParameters(
			new SqlParameter(P_MAIL	,Types.VARCHAR),
			new SqlParameter(P_ASWORD	,Types.VARCHAR),
			new SqlParameter(P_IRSTNAME	,Types.VARCHAR),
			new SqlParameter(P_MIDDLENAME	,Types.VARCHAR),
			new SqlParameter(P_ASTNAME	,Types.VARCHAR)
		)
		.returningResultSet("user", new UserMapper());

	}


	public AppUser addUser(AppUser user) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_MAIL, user.getEmail());
		in.addValue(P_ASWORD, user.getPassword());
		in.addValue(P_IRSTNAME, user.getFirstName());
		in.addValue(P_ASTNAME, user.getLastName());
		in.addValue(P_MIDDLENAME, user.getMiddleName());
		Map<String, Object> out = addOrUpdateUserSproc.execute(in);
		List<AppUser> result = (List<AppUser>) out.get("user");
		return result.get(0);
		
	}


	public AppUser getUserByIdentifier(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static class UserMapper implements RowMapper<AppUser> {

		@Override
		public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			// AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
			AppUser appUser = new AppUser();
			appUser.setAppUserId(rs.getInt(1));
			return appUser;
		}
	}
}
