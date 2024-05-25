package org.example.models;

import lombok.Data;

import java.util.Date;
@Data
public class SalesReceiptDetail {
    private int detailID;
    private int salesReceiptID;
    private int productID;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
