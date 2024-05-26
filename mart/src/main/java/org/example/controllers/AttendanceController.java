package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AttendanceController {
    private Connection connection;

    public AttendanceController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }


    public boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift) {
        String query = "INSERT INTO Attendance (EmployeeID, Date, HoursWorked, Shift, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
