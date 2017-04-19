package com.coachqa.repository.dao.sp;

import com.coachqa.ws.model.AnswerModel;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;

public class AnswerAddSproc
{
	private SimpleJdbcCall m_AnswerAddSprocSproc;

	private static String P_QUESTIONID		 = "QuestionId";
	private static String P_ANSWEREDBYUSERID		 = "AnsweredByUserId";
	private static String P_CONTENT		 = "Content";


	public AnswerAddSproc(DataSource m_dataSource)
	{
		m_AnswerAddSprocSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("AnswerAddSproc")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
				P_QUESTIONID,
				P_ANSWEREDBYUSERID,
				P_CONTENT
		)
		.declareParameters(
			new SqlParameter(P_QUESTIONID	,Types.INTEGER),
			new SqlParameter(P_ANSWEREDBYUSERID	,Types.INTEGER),
			new SqlParameter(P_CONTENT	,Types.LONGVARCHAR)
		);
	}


	public void addAnswer(AnswerModel answer) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_QUESTIONID, answer.getQuestionId());
		in.addValue(P_ANSWEREDBYUSERID, answer.getAnsweredByUserId());
		in.addValue(P_CONTENT, answer.getContent());
		Map<String, Object> out = m_AnswerAddSprocSproc.execute(in);
	}
}
