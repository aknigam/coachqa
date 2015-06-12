package com.coachqa.repository.dao.sp;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.mapper.ClassRoomMapper;

public class ClassroomGetByIdSproc
{
	private SimpleJdbcCall m_classroomGetByIdSproc;

	private static String P_CLASSROOMID		 = "ClassroomId";

	public ClassroomGetByIdSproc(DataSource m_dataSource)
	{
		m_classroomGetByIdSproc = new SimpleJdbcCall(m_dataSource)
		.withProcedureName("classroomGetById")
		.withoutProcedureColumnMetaDataAccess()
		.useInParameterNames(
			P_CLASSROOMID
		)
		.declareParameters(
			new SqlParameter(P_CLASSROOMID	,Types.INTEGER)
		)
		.returningResultSet("classroom", new ClassRoomMapper());
		;
	}

	public Classroom getClassroomByIdentifier(Integer classroomId) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue(P_CLASSROOMID, classroomId);
		Map<String, Object> out = m_classroomGetByIdSproc.execute(in);
		
		List<Classroom> classrooms = (List<Classroom>) out.get("classroom");
		return classrooms.get(0);
	}
}
