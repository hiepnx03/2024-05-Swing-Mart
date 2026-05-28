package org.example.mapper;

import org.example.models.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet rs) throws SQLException {
        return UserRole.builder()
                .userRoleID(rs.getInt("userRoleID"))
                .userID(rs.getInt("userID"))
                .roleID(rs.getInt("roleID"))
                .build();
    }
}
