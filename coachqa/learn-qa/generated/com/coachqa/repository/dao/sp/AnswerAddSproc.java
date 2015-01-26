package com.coachqa.repository.dao.sp;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class AnswerAddSproc
{
	private SimpleJdbcCall m_AnswerAddSprocSproc;

	private static String P_UESTIONID		 = "QuestionId";
	private static String P_NSWEREDBYUSERID		 = "AnsweredByUserId";
	private static String P_ONTENT		 = "Content";


	public AnswerAddSproc(DataSource m_dataSource)
	{
		m_AnswerAddSprocSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("AnswerAddSproc")
		.withCatalogName("null")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_UESTIONID,
			P_NSWEREDBYUSERID,
			P_ONTENT
		)
		.declareParameters(
			new SqlParameter(P_UESTIONID	,Types.INTEGER),
			new SqlParameter(P_NSWEREDBYUSERID	,Types.INTEGER),
			new SqlParameter(P_ONTENT	,Types.LONGVARCHAR)
		);
	}
}
