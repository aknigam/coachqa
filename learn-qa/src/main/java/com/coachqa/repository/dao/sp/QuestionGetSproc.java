package com.coachqa.repository.dao.sp;

import com.coachqa.repository.dao.mapper.AnswerSprocMapper;
import com.coachqa.repository.dao.mapper.QuestionMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.Types;

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
		.returningResultSet("answers", new AnswerSprocMapper())
		.returningResultSet("tags", new TagJDBCMapper());
	}



	
	
}
