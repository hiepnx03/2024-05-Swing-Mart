package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;
import org.example.repositories.UserRepository;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_GET_USER_ID = "SELECT userID FROM users WHERE username = ?";
    private static final String SQL_LOGIN_WITH_ROLE = 
        "SELECT u.*, r.* FROM users u " +
        "INNER JOIN userroles ur ON u.userID = ur.userID " +
        "INNER JOIN roles r ON ur.roleID = r.roleID " +
        "WHERE u.username = ? AND u.passwordHash = ? AND r.roleName = ?";
    private static final String SQL_FIND_ROLE_BY_USER_ID = 
        "SELECT r.* FROM roles r INNER JOIN userroles ur ON r.roleID = ur.roleID WHERE ur.userID = ?";
    private static final String SQL_FIND_EMPLOYEE_BY_USER_ID = 
        "SELECT CONCAT(e.firstName, ' ', e.lastName) AS fullName FROM employees e " +
        "INNER JOIN users u ON e.employeeID = u.employeeID WHERE u.userID = ?";
    private static final String SQL_GET_ALL_ROLES = "SELECT * FROM roles";
    private static final String SQL_GET_ALL_EMPLOYEES_SIMPLE = "SELECT * FROM employees";
    private static final String SQL_GET_ALL_USER_INFO = 
        "SELECT u.userID, u.username, u.passwordHash, u.email, e.firstName, e.lastName, e.position, r.roleName " +
        "FROM users u " +
        "INNER JOIN employees e ON u.employeeID = e.employeeID " +
        "INNER JOIN userroles ur ON u.userID = ur.userID " +
        "INNER JOIN roles r ON ur.roleID = r.roleID";
    private static final String SQL_ASSIGN_USER_ROLE = "INSERT INTO userroles (userID, roleID) VALUES (?, ?)";
    private static final String SQL_FIND_EMPLOYEE_ID_BY_FULL_NAME = 
        "SELECT employeeID FROM employees WHERE CONCAT(firstName, ' ', lastName) = ?";
    private static final String SQL_FIND_ROLE_ID_BY_ROLE_NAME = "SELECT roleID FROM roles WHERE roleName = ?";
    private static final String SQL_FIND_ROLE_BY_NAME = "SELECT * FROM roles WHERE roleName = ?";
    
    private static final String SQL_CHECK_USERNAME = "SELECT COUNT(*) FROM users WHERE username = ?";
    private static final String SQL_CHECK_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String SQL_CHECK_EMPLOYEE_ID_BY_NAME = 
        "SELECT COUNT(*) FROM users WHERE employeeID = (SELECT employeeID FROM employees WHERE CONCAT(firstName, ' ', lastName) = ?)";
    private static final String SQL_INSERT_USER = 
        "INSERT INTO users (username, passwordHash, email, employeeID) SELECT ?, ?, ?, e.employeeID FROM employees e WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";
    private static final String SQL_INSERT_USER_ROLE = 
        "INSERT INTO userroles (userID, roleID) SELECT u.userID, r.roleID FROM users u INNER JOIN employees e ON u.employeeID = e.employeeID " +
        "INNER JOIN roles r ON r.roleID = ? WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";
        
    private static final String SQL_DELETE_USER_ROLE = "DELETE FROM userroles WHERE userID = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE userID = ?";
    
    private static final String SQL_UPDATE_USER = 
        "UPDATE users u INNER JOIN employees e ON u.employeeID = e.employeeID SET u.username = ?, u.passwordHash = ?, u.email = ? WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";
    private static final String SQL_UPDATE_USER_ROLE = 
        "UPDATE userroles ur INNER JOIN users u ON ur.userID = u.userID SET ur.roleID = ? WHERE u.username = ?";
        
    private static final String SQL_GET_EMPLOYEE_BY_ID_AND_USER_ID = "SELECT * FROM employees WHERE EmployeeID = ? AND CreatedBy = ?";
    private static final String SQL_GET_EMPLOYEE_ID_BY_USER_ID = "SELECT employeeID FROM users WHERE userID = ?";

    @Override
    public int getUserID(String username) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_USER_ID)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("userID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean loginWithRole(String username, String password, String roleName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_LOGIN_WITH_ROLE)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Role findRoleByUserId(int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_ROLE_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Role role = new Role();
                    role.setRoleID(resultSet.getInt("roleID"));
                    role.setRoleName(resultSet.getString("roleName"));
                    role.setDescription(resultSet.getString("description"));
                    return role;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findEmployeeByUserId(int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_EMPLOYEE_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("fullName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try (Connection conn = MyConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROLES)) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleID(resultSet.getInt("roleID"));
                role.setRoleName(resultSet.getString("roleName"));
                role.setDescription(resultSet.getString("description"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = MyConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_EMPLOYEES_SIMPLE)) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public DefaultTableModel getAllUserInfoTableModel() {
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_USER_INFO)) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"UserID", "Username", "PasswordHash", "Email", "FirstName", "LastName", "Position", "RoleName"}, 0);

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String passwordHash = resultSet.getString("passwordHash");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String position = resultSet.getString("position");
                String roleName = resultSet.getString("roleName");

                model.addRow(new Object[]{userID, username, passwordHash, email, firstName, lastName, position, roleName});
            }
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void assignUserRole(int userID, int roleID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ASSIGN_USER_ROLE)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, roleID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findEmployeeIDByFullName(String fullName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_EMPLOYEE_ID_BY_FULL_NAME)) {
            preparedStatement.setString(1, fullName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("employeeID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int findRoleIDByRoleName(String roleName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_ROLE_ID_BY_ROLE_NAME)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("roleID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Role findRoleByName(String roleName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_ROLE_BY_NAME)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Role role = new Role();
                    role.setRoleID(resultSet.getInt("roleID"));
                    role.setRoleName(resultSet.getString("roleName"));
                    role.setDescription(resultSet.getString("description"));
                    return role;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String addUser(User user, String employeeName, int roleID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement checkUsernameStmt = conn.prepareStatement(SQL_CHECK_USERNAME);
             PreparedStatement checkEmailStmt = conn.prepareStatement(SQL_CHECK_EMAIL);
             PreparedStatement checkEmployeeIDStmt = conn.prepareStatement(SQL_CHECK_EMPLOYEE_ID_BY_NAME);
             PreparedStatement insertUserStmt = conn.prepareStatement(SQL_INSERT_USER);
             PreparedStatement insertUserRoleStmt = conn.prepareStatement(SQL_INSERT_USER_ROLE)) {

            checkUsernameStmt.setString(1, user.getUsername());
            try (ResultSet resultSetUsername = checkUsernameStmt.executeQuery()) {
                if (resultSetUsername.next() && resultSetUsername.getInt(1) > 0) {
                    return "Username already exists.";
                }
            }

            checkEmailStmt.setString(1, user.getEmail());
            try (ResultSet resultSetEmail = checkEmailStmt.executeQuery()) {
                if (resultSetEmail.next() && resultSetEmail.getInt(1) > 0) {
                    return "Email already exists.";
                }
            }

            checkEmployeeIDStmt.setString(1, employeeName);
            try (ResultSet resultSetEmployeeID = checkEmployeeIDStmt.executeQuery()) {
                if (resultSetEmployeeID.next() && resultSetEmployeeID.getInt(1) > 0) {
                    return "Employee ID already exists.";
                }
            }

            insertUserStmt.setString(1, user.getUsername());
            insertUserStmt.setString(2, user.getPasswordHash());
            insertUserStmt.setString(3, user.getEmail());
            insertUserStmt.setString(4, employeeName);

            int rowsAffectedUser = insertUserStmt.executeUpdate();

            if (rowsAffectedUser > 0) {
                insertUserRoleStmt.setInt(1, roleID);
                insertUserRoleStmt.setString(2, employeeName);
                int rowsAffectedUserRole = insertUserRoleStmt.executeUpdate();

                if (rowsAffectedUserRole > 0) {
                    return "Success";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed to add user.";
    }

    @Override
    public boolean deleteUser(int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatementUserRole = conn.prepareStatement(SQL_DELETE_USER_ROLE);
             PreparedStatement preparedStatementUser = conn.prepareStatement(SQL_DELETE_USER)) {

            preparedStatementUserRole.setInt(1, userID);
            preparedStatementUserRole.executeUpdate();

            preparedStatementUser.setInt(1, userID);
            int rowsAffected = preparedStatementUser.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user, String employeeName, int roleID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatementUser = conn.prepareStatement(SQL_UPDATE_USER);
             PreparedStatement preparedStatementUserRole = conn.prepareStatement(SQL_UPDATE_USER_ROLE)) {

            preparedStatementUser.setString(1, user.getUsername());
            preparedStatementUser.setString(2, user.getPasswordHash());
            preparedStatementUser.setString(3, user.getEmail());
            preparedStatementUser.setString(4, employeeName);

            int rowsAffectedUser = preparedStatementUser.executeUpdate();

            if (rowsAffectedUser > 0) {
                preparedStatementUserRole.setInt(1, roleID);
                preparedStatementUserRole.setString(2, user.getUsername());
                int rowsAffectedUserRole = preparedStatementUserRole.executeUpdate();

                if (rowsAffectedUserRole > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee getByEmployeeIDWithUserID(int employeeID, int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_EMPLOYEE_BY_ID_AND_USER_ID)) {

            preparedStatement.setInt(1, employeeID);
            preparedStatement.setInt(2, userID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(resultSet.getInt("EmployeeID"));
                    employee.setFirstName(resultSet.getString("FirstName"));
                    employee.setLastName(resultSet.getString("LastName"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getEmployeeIDByUserID(int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_EMPLOYEE_ID_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("employeeID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
