package org.example.repositories;

import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface UserRepository {
    int getUserID(String username);
    boolean loginWithRole(String username, String password, String roleName);
    Role findRoleByUserId(int userID);
    String findEmployeeByUserId(int userID);
    List<Role> getAllRoles();
    List<Employee> getAllEmployees();
    DefaultTableModel getAllUserInfoTableModel();
    void assignUserRole(int userID, int roleID);
    int findEmployeeIDByFullName(String fullName);
    int findRoleIDByRoleName(String roleName);
    Role findRoleByName(String roleName);
    String addUser(User user, String employeeName, int roleID);
    boolean deleteUser(int userID);
    boolean updateUser(User user, String employeeName, int roleID);
    Employee getByEmployeeIDWithUserID(int employeeID, int userID);
    int getEmployeeIDByUserID(int userID);
}
