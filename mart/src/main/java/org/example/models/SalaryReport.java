package org.example.models;


import lombok.Data;

import java.sql.Date;
@Data
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

