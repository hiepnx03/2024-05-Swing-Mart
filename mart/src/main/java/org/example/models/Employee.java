package org.example.models;

import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
@Data
@Table(name = "employees")
public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String position;
    private Date dateOfBirth;
    private String contactInfo;
    private Date hireDate;
    private double salary;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    public String getFullName() {
        return firstName + " " + lastName;
    }
    // Constructors, getters, and setters
}
