package org.example.models;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userroles")
public class UserRole {
    private int userRoleID;
    private int userID;
    private int roleID;

    // Constructors, getters, and setters
}
