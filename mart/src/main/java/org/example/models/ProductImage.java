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
public class ProductImage {
    private int imageID;
    private int productID;
    private String imageUrl;
    private Date createdAt;

    // Constructors, getters, and setters
}
