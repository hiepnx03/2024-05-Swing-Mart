package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
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

