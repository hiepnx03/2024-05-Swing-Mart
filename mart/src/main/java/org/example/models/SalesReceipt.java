package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
public class SalesReceipt {
    private int salesReceiptID;
    private int employeeID;
    private Date saleDate;
    private double totalAmount;
    private String customerName;
    private String paymentMethod;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
