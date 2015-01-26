package com.coachqa.repository.dao;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class addOrUpdateUser
{
	private SimpleJdbcCall m_addOrUpdateUserSproc;

	private static String P_PPUSERID		 = "AppUserId";
	private static String P_SERREPUTATIONID		 = "UserReputationId";
	private static String P_MAIL		 = "Email";
	private static String P_ASWORD		 = "Pasword";
	private static String P_IRSTNAME		 = "FirstName";
	private static String P_ASTNAME		 = "LastName";


	public addOrUpdateUser(DataSource m_dataSource)
	{
		m_addOrUpdateUserSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("addOrUpdateUser")
		.withCatalogName("null")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_PPUSERID,
			P_SERREPUTATIONID,
			P_MAIL,
			P_ASWORD,
			P_IRSTNAME,
			P_ASTNAME
		)
		.declareParameters(
			new SqlParameter(P_MAIL	,Types.VARCHAR),
			new SqlParameter(P_ASWORD	,Types.VARCHAR),
			new SqlParameter(P_IRSTNAME	,Types.VARCHAR),
			new SqlParameter(P_ASTNAME	,Types.VARCHAR),
			new SqlParameter(P_PPUSERID	,Types.INTEGER),
			new SqlParameter(P_SERREPUTATIONID	,Types.INTEGER)
		);
	}
}
