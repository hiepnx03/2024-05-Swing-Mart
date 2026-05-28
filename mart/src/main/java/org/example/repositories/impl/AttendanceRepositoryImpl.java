package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.repositories.AttendanceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AttendanceRepositoryImpl implements AttendanceRepository {

    private static final String SQL_INSERT_ATTENDANCE = 
        "INSERT INTO Attendance (EmployeeID, Date, HoursWorked, Shift, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_ATTENDANCE)) {
            stmt.setInt(1, employeeID);
            stmt.setDate(2, (java.sql.Date) date);
            stmt.setInt(3, hoursWorked);
            stmt.setString(4, shift);
            stmt.setInt(5, 1); // Replace with the actual created by user ID
            stmt.setInt(6, 1); // Replace with the actual updated by user ID
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
