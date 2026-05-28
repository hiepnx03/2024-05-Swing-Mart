package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private int attendanceID;
    private int employeeID;
    private Date date;
    private double hoursWorked;
    private String shift;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
