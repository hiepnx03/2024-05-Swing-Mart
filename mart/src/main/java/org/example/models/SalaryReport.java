package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryReport {
    private int reportID;
    private int employeeID;
    private Date reportDate;
    private String period;
    private double totalSalary;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}

