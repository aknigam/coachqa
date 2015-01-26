package com.coachqa.repository.dao.sp;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class QuestionGet
{
	private SimpleJdbcCall m_QuestionGetSproc;

	private static String P_QUESTIONID		 = "QuestionId";


	public QuestionGet(DataSource m_dataSource)
	{
		m_QuestionGetSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("QuestionGet")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_UESTIONID
		)
		.declareParameters(
			new SqlParameter(P_QUESTIONID	,Types.INTEGER)
		);
	}
}
