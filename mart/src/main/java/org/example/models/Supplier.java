package org.example.models;

import lombok.Data;

import java.util.Date;
@Data
public class Supplier {
    private int supplierID;
    private String supplierName;
    private String contactInfo;
    private String address;
    private String phone;
    private String email;
    private int createdBy;
    private int updatedBy;
    private Date updatedAt;

    // Constructors, getters, and setters
}
