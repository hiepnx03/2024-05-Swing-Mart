package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
public class Order {
    private int orderID;
    private int supplierID;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
