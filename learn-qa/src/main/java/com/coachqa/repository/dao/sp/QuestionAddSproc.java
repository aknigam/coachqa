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

public class QuestionAddSproc
{


	private static final String P_ISPUBLIC = "pIsPublic";
	private SimpleJdbcCall addOrUpdateUserSproc;

	private static String P_POSTEDBY		 = "pPostedBy";
	private static String P_TITLE		 = "pTitle";
	private static String P_CONTENT		 = "pContent";
	private static final String P_SUBJECT = "pRefSubjectId";
	private static final String P_CLASSROOMID = "pClassroomId";

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


	public Question addQuestion(Question model) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_POSTEDBY, model.getPostedBy().getAppUserId());
		in.addValue(P_TITLE, model.getTitle());
		in.addValue(P_CONTENT, model.getContent());
		in.addValue(P_SUBJECT, model.getRefSubjectId());
		if(model.getClassroomId() != null)
			in.addValue(P_CLASSROOMID,  model.getClassroomId());
		else
			in.addValue(P_CLASSROOMID,  null);
		in.addValue(P_ISPUBLIC,  model.isPublicQuestion());

		
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
