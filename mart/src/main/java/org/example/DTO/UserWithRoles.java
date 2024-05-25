package org.example.DTO;

import lombok.Data;
import org.example.models.Role;
import org.example.models.User;

@Data
public class UserWithRoles {

    private User user;
    private Role role;

    public UserWithRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}

