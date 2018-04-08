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
		
		classroom.setClassroomId(rs.getInt("classroomid"));
		classroom.setClassName(rs.getString("classname"));
		classroom.setIsPublic(rs.getBoolean("ispublic"));
		classroom.setClassName(rs.getString("classname"));
		
		AppUser owner = new AppUser();
		owner.setAppUserId(rs.getInt("postedby"));
		owner.setFirstName(rs.getString("firstname"));
		owner.setMiddleName(rs.getString("middlename"));
		owner.setLastName(rs.getString("lastName"));
		
		classroom.setClassOwner(owner);
		return classroom;
	}

}
