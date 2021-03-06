package com.coachqa.repository.dao.sp;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.repository.dao.mapper.QuestionMapper;
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

@Deprecated
public class QuestionAddSproc
{


	private static final String P_ISPUBLIC = "pispublic";
	private SimpleJdbcCall addOrUpdateUserSproc;

	private static String P_POSTEDBY		 = "ppostedby";
	private static String P_TITLE		 = "ptitle";
	private static String P_CONTENT		 = "pcontent";
	private static final String P_SUBJECT = "prefsubjectid";
	private static final String P_CLASSROOMID = "pclassroomid";

	public QuestionAddSproc(DataSource dataSource)
	{
		addOrUpdateUserSproc = new SimpleJdbcCall(dataSource)
		.withProcedureName("questionAddSproc")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
				P_POSTEDBY,
				P_TITLE,
				P_CONTENT,
				P_SUBJECT,
				P_CLASSROOMID,
				P_ISPUBLIC
		)
		.declareParameters(
			new SqlParameter(P_POSTEDBY	,Types.INTEGER),
			new SqlParameter(P_TITLE	,Types.VARCHAR),
			new SqlParameter(P_CONTENT	,Types.VARCHAR),
			new SqlParameter(P_SUBJECT, Types.INTEGER),
				new SqlParameter(P_CLASSROOMID, Types.INTEGER),
				new SqlParameter(P_ISPUBLIC, Types.TINYINT)
		)
		.returningResultSet("question", new QuestionMapper());

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
