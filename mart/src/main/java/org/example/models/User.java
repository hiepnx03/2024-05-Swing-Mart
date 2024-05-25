package org.example.models;

import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
@Data
@Table(name = "users")
public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private String email;
    private int employeeID;
    private Date createdAt;


    public User(String username, String passwordHash, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }
    // Constructors, getters, and setters
}

