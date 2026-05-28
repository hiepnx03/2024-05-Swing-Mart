package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
