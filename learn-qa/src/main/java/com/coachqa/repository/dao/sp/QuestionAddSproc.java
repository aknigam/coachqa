package com.coachqa.repository.dao.sp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Question;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import com.coachqa.ws.model.QuestionModel;

public class QuestionAddSproc
{
	

	private SimpleJdbcCall addOrUpdateUserSproc;

	private static String P_POSTEDBY		 = "pPostedBy";
	private static String P_TITLE		 = "pTitle";
	private static String P_CONTENT		 = "pContent";
	private static final String P_SUBJECT = "pRefSubjectId";

	public QuestionAddSproc(DataSource dataSource)
	{
		addOrUpdateUserSproc = new SimpleJdbcCall(dataSource)
		.withProcedureName("questionAddSproc")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
				P_POSTEDBY,
				P_TITLE,
				P_CONTENT
		)
		.declareParameters(
			new SqlParameter(P_POSTEDBY	,Types.INTEGER),
			new SqlParameter(P_TITLE	,Types.VARCHAR),
			new SqlParameter(P_CONTENT	,Types.VARCHAR),
			new SqlParameter(P_SUBJECT, Types.INTEGER)
		)
		.returningResultSet("question", new QuestionMapper());

	}


	public Question addQuestion(QuestionModel model) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_POSTEDBY, model.getPostedBy());
		in.addValue(P_TITLE, model.getTitle());
		in.addValue(P_SUBJECT, model.getRefSubjectId());
		in.addValue(P_CONTENT, model.getContent());
		
		Map<String, Object> out = addOrUpdateUserSproc.execute(in);
		
		List<Question> result = (List<Question>) out.get("question");
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
