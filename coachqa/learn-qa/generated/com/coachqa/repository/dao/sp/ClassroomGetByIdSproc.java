package com.coachqa.repository.dao.sp;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ClassroomGetByIdSproc
{
	private SimpleJdbcCall m_classroomGetByIdSproc;

	private static String P_CLASSROOMID		 = "ClassroomId";


	public classroomGetById(DataSource m_dataSource)
	{
		m_classroomGetByIdSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("classroomGetById")
		.withCatalogName("null")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_CLASSROOMID
		)
		.declareParameters(
			new SqlParameter(P_CLASSROOMID	,Types.INTEGER)
		);
	}
}
