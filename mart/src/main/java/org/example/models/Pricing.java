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
public class Pricing {
    private int priceID;
    private int productID;
    private double price;
    private Date startDate;
    private Date endDate;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}

