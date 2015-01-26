package com.coachqa.repository.dao.sp;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class questionUpdateStats
{
	private SimpleJdbcCall m_questionUpdateStatsSproc;

	private static String P_UESTIONID		 = "QuestionId";
	private static String P_OTES		 = "Votes";


	public questionUpdateStats(DataSource m_dataSource)
	{
		m_questionUpdateStatsSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("questionUpdateStats")
		.withCatalogName("null")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_UESTIONID,
			P_OTES
		)
		.declareParameters(
			new SqlParameter(P_UESTIONID	,Types.INTEGER),
			new SqlParameter(P_OTES	,Types.INTEGER)
		);
	}
}
