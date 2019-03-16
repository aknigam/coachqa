package com.coachqa.repository.dao.sp;

import com.coachqa.entity.Answer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;

public class AnswerAddSproc
{
	private SimpleJdbcCall m_AnswerAddSprocSproc;

	private static String P_QUESTIONID		 = "questionid";
	private static String P_ANSWEREDBYUSERID		 = "answeredbyuserid";
	private static String P_CONTENT		 = "content";
	private static String P_ACCOUNTID		 = "accountId";


	public AnswerAddSproc(DataSource m_dataSource)
	{
		m_AnswerAddSprocSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("answeraddsproc")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
				P_QUESTIONID,
				P_ANSWEREDBYUSERID,
				P_CONTENT,
				P_ACCOUNTID
		)
		.declareParameters(
			new SqlParameter(P_QUESTIONID	,Types.INTEGER),
			new SqlParameter(P_ANSWEREDBYUSERID	,Types.INTEGER),
			new SqlParameter(P_CONTENT	,Types.LONGVARCHAR),
				new SqlParameter(P_ACCOUNTID	,Types.INTEGER)
		);
	}


	public void addAnswer(Answer answer) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_QUESTIONID, answer.getQuestionId());
		in.addValue(P_ANSWEREDBYUSERID, answer.getAnsweredByUserId());
		in.addValue(P_CONTENT, answer.getContent());
		in.addValue(P_ACCOUNTID, answer.getAccount().getAccountId());
		Map<String, Object> out = m_AnswerAddSprocSproc.execute(in);
	}
}
