package com.coachqa.repository.dao.sp;

import com.coachqa.entity.Answer;
import com.coachqa.entity.Question;
import com.coachqa.repository.dao.mapper.AnswerMapper;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Deprecated
public class QuestionGetSproc
{
	private SimpleJdbcCall m_QuestionGetSproc;

	private static String P_QUESTIONID		 = "pQuestionId";


	public QuestionGetSproc(DataSource m_dataSource)
	{
		m_QuestionGetSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("QuestionGet")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
				P_QUESTIONID
		)
		.declareParameters(
			new SqlParameter(P_QUESTIONID	,Types.INTEGER)
		)
		.returningResultSet("question", new QuestionMapper())
		.returningResultSet("answers", new AnswerMapper())
		.returningResultSet("tags", new TagJDBCMapper());
	}



	
	
}
