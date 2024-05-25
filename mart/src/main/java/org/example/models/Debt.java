package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
public class Debt {
    private int debtID;
    private int supplierID;
    private double amount;
    private Date dueDate;
    private String status;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
