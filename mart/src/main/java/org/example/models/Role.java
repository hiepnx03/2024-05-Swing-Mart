package org.example.models;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    private int roleID;
    private String roleName;
    private String description;

    // Constructors, getters, and setters
}
