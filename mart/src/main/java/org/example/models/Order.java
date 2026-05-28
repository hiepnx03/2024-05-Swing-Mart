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
