package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
public class InventoryReceipt {
    private int receiptID;
    private int orderID;
    private Date receiptDate;
    private double totalAmount;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
