package com.coachqa.repository.dao.mybatis.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassSqlProvider {

    public String getClassroomSql(Integer classroomId){



        String sql =  new SQL()
                .SELECT(" classroomid,minreputationtojoinid,classowner ,u.firstname,u.middlename,u.lastName," +
                        "classname,ispublic, subjectId")
                .FROM("classroom c")
                .JOIN("appuser u ON u.appuserId = classowner where c.classroomid = <#classroomId>")
                .toString();

        return sql;

    }

}
