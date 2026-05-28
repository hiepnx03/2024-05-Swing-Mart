package org.example.mapper;

import org.example.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        return User.builder()
                .userID(rs.getInt("userID"))
                .username(rs.getString("username"))
                .passwordHash(rs.getString("passwordHash"))
                .email(rs.getString("email"))
                .employeeID(rs.getInt("employeeID"))
                .createdAt(rs.getDate("createdAt"))
                .build();
    }
}
