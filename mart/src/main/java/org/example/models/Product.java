package org.example.models;

import lombok.Data;

import java.util.Date;
@Data
public class Product {
    private int productID;
    private String productName;
    private String category;
    private int stockQuantity;
    private double unitPrice;
    private int supplierID;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
