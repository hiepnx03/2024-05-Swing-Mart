package org.example.util;

import org.example.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        User user = new User(
            rs.getString("username"),
            rs.getString("passwordHash"),
            rs.getString("email")
        );
        user.setUserID(rs.getInt("userID"));
        user.setEmployeeID(rs.getInt("employeeID"));
        user.setCreatedAt(rs.getDate("createdAt"));
        return user;
    }
}
