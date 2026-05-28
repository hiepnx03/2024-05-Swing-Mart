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
