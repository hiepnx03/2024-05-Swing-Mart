package org.example.mapper;

import org.example.models.Attendance;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRowMapper implements RowMapper<Attendance> {
    @Override
    public Attendance mapRow(ResultSet rs) throws SQLException {
        return Attendance.builder()
                .attendanceID(rs.getInt("attendanceID"))
                .employeeID(rs.getInt("employeeID"))
                .date(rs.getDate("date"))
                .hoursWorked(rs.getDouble("hoursWorked"))
                .shift(rs.getString("shift"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
