package org.example.models;

import lombok.Data;

import java.sql.Date;
@Data
public class ProductImage {
    private int imageID;
    private int productID;
    private String imageUrl;
    private Date createdAt;

    // Constructors, getters, and setters
}
