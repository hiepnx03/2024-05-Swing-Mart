package org.example.services.impl;

import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.repositories.impl.UserRepositoryImpl;
import org.example.services.UserService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public int getUserID(String username) {
        return userRepository.getUserID(username);
    }

    @Override
    public boolean loginWithRole(String username, String password, String roleName) {
        return userRepository.loginWithRole(username, password, roleName);
    }

    @Override
    public Role findRoleByUserId(int userID) {
        return userRepository.findRoleByUserId(userID);
    }

    @Override
    public String findEmployeeByUserId(int userID) {
        return userRepository.findEmployeeByUserId(userID);
    }

    @Override
    public List<Role> getAllRoles() {
        return userRepository.getAllRoles();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return userRepository.getAllEmployees();
    }

    @Override
    public DefaultTableModel getAllUserInfoTableModel() {
        return userRepository.getAllUserInfoTableModel();
    }

    @Override
    public void assignUserRole(int userID, int roleID) {
        userRepository.assignUserRole(userID, roleID);
    }

    @Override
    public int findEmployeeIDByFullName(String fullName) {
        return userRepository.findEmployeeIDByFullName(fullName);
    }

    @Override
    public int findRoleIDByRoleName(String roleName) {
        return userRepository.findRoleIDByRoleName(roleName);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return userRepository.findRoleByName(roleName);
    }

    @Override
    public String addUser(User user, String employeeName, int roleID) {
        return userRepository.addUser(user, employeeName, roleID);
    }

    @Override
    public boolean deleteUser(int userID) {
        return userRepository.deleteUser(userID);
    }

    @Override
    public boolean updateUser(User user, String employeeName, int roleID) {
        return userRepository.updateUser(user, employeeName, roleID);
    }

    @Override
    public Employee getByEmployeeIDWithUserID(int employeeID, int userID) {
        return userRepository.getByEmployeeIDWithUserID(employeeID, userID);
    }

    @Override
    public int getEmployeeIDByUserID(int userID) {
        return userRepository.getEmployeeIDByUserID(userID);
    }
}
