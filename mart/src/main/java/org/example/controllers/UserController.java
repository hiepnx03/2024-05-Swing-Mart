package org.example.controllers;

import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;
import org.example.services.UserService;
import org.example.services.impl.UserServiceImpl;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {

    private final UserService userService;
    private int loggedInUserID = -1; // Default value indicating no user is logged in
    private Map<Integer, Integer> sessionUserMap = new HashMap<>(); // Map to store user IDs in session

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public int getUserID(String username) {
        return userService.getUserID(username);
    }

    public void setUserIDInSession(int userID) {
        this.loggedInUserID = userID;
        this.sessionUserMap.put(userID, userID);
    }

    public boolean loginWithRole(String username, String password, String roleName) {
        return userService.loginWithRole(username, password, roleName);
    }

    public Role findRoleByUserId(int userID) {
        return userService.findRoleByUserId(userID);
    }

    public String findEmployeeByUserId(int userID) {
        return userService.findEmployeeByUserId(userID);
    }

    public List<Role> getAllRoles() {
        return userService.getAllRoles();
    }

    public List<Employee> getAllEmployees() {
        return userService.getAllEmployees();
    }

    public DefaultTableModel getAllUserInfoTableModel() {
        return userService.getAllUserInfoTableModel();
    }

    public void assignUserRole(int userID, int roleID) {
        userService.assignUserRole(userID, roleID);
    }

    public int findEmployeeIDByFullName(String fullName) {
        return userService.findEmployeeIDByFullName(fullName);
    }

    public int findRoleIDByRoleName(String roleName) {
        return userService.findRoleIDByRoleName(roleName);
    }

    public Role findRoleByName(String roleName) {
        return userService.findRoleByName(roleName);
    }

    public String addUser(User user, String employeeName, int roleID) {
        return userService.addUser(user, employeeName, roleID);
    }

    public boolean deleteUser(int userID) {
        return userService.deleteUser(userID);
    }

    public boolean updateUser(User user, String employeeName, int roleID) {
        return userService.updateUser(user, employeeName, roleID);
    }

    public Employee getByEmployeeIDWithUserID(int employeeID, int userID) {
        return userService.getByEmployeeIDWithUserID(employeeID, userID);
    }

    public int getEmployeeIDByUserID(int userID) {
        return userService.getEmployeeIDByUserID(userID);
    }
}
