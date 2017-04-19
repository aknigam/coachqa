package com.coachqa.repository.dao.mapper;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassRoomMapper implements RowMapper<Classroom> {
	
	@Override
	public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Classroom classroom = new Classroom();
		
		classroom.setClassroomId(rs.getInt("ClassroomId"));
		classroom.setClassName(rs.getString("ClassName"));
		classroom.setIsPublic(rs.getBoolean("IsPublic"));
		classroom.setClassName(rs.getString("ClassName"));
		
		AppUser owner = new AppUser();
		owner.setAppUserId(rs.getInt("PostedBy"));
		owner.setFirstName(rs.getString("Firstname"));
		owner.setMiddleName(rs.getString("middleName"));
		owner.setLastName(rs.getString("lastName"));
		
		classroom.setClassOwner(owner);
		return classroom;
	}

}
