package org.example.mapper;

import org.example.models.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs) throws SQLException {
        return Role.builder()
                .roleID(rs.getInt("roleID"))
                .roleName(rs.getString("roleName"))
                .description(rs.getString("description"))
                .build();
    }
}
