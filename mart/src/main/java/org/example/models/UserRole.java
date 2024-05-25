package org.example.models;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "userroles")
public class UserRole {
    private int userRoleID;
    private int userID;
    private int roleID;

    // Constructors, getters, and setters
}
