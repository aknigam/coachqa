package com.coachqa.repository.dao.mybatis.typehandler;

import com.coachqa.enums.ClassroomMembershipStatusEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomStatusTypeHandler implements TypeHandler<ClassroomMembershipStatusEnum> {
    @Override
    public void setParameter(PreparedStatement ps, int i, ClassroomMembershipStatusEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public ClassroomMembershipStatusEnum getResult(ResultSet rs, String columnName) throws SQLException {

        int postTypeId = rs.getInt(columnName);
        // TODO: 20/01/18 implement fromId method in the enum

        switch (postTypeId)
        {
            case 0:
                return ClassroomMembershipStatusEnum.NOT_MEMBER;
            case 1:
                return ClassroomMembershipStatusEnum.PENDING_APPROVAL;
            case 2:
                return ClassroomMembershipStatusEnum.ACTIVE;
            case 3:
                return ClassroomMembershipStatusEnum.EXPIRED;
            case 4:
                return ClassroomMembershipStatusEnum.REJECTED;
            default:
                throw new RuntimeException("Unknown classroom status "+ postTypeId);




        }

    }

    @Override
    public ClassroomMembershipStatusEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int postTypeId = rs.getInt(columnIndex);
        // TODO: 20/01/18 implement fromId method in the enum
        switch (postTypeId)
        {
            case 0:
                return ClassroomMembershipStatusEnum.NOT_MEMBER;
            case 1:
                return ClassroomMembershipStatusEnum.PENDING_APPROVAL;
            case 2:
                return ClassroomMembershipStatusEnum.ACTIVE;
            case 3:
                return ClassroomMembershipStatusEnum.EXPIRED;
            case 4:
                return ClassroomMembershipStatusEnum.REJECTED;
            default:
                throw new RuntimeException("Unknown classroom status "+ postTypeId);




        }

    }

    @Override
    public ClassroomMembershipStatusEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
