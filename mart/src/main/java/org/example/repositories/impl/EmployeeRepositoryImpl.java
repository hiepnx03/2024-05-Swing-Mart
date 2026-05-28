package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.models.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.mapper.EmployeeRowMapper;
import org.example.mapper.RowMapper;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String SQL_GET_ALL_NHAN_VIEN = 
        "SELECT e.*, r.roleID, r.roleName " +
        "FROM employees e " +
        "LEFT JOIN users u ON e.employeeID = u.employeeID " +
        "LEFT JOIN userroles ur ON u.userID = ur.userID " +
        "LEFT JOIN roles r ON ur.roleID = r.roleID";

    private static final String SQL_GET_ALL_NHAN_VIEN_TABLE_MODEL = 
        "SELECT e.employeeID, e.firstName, e.lastName, e.position, e.dateOfBirth, e.contactInfo, e.hireDate, e.salary, u.username, r.roleName " +
        "FROM employees e " +
        "LEFT JOIN users u ON e.employeeID = u.employeeID " +
        "LEFT JOIN userroles ur ON u.userID = ur.userID " +
        "LEFT JOIN roles r ON ur.roleID = r.roleID";

    private static final String SQL_ADD_EMPLOYEE = 
        "INSERT INTO employees (firstName, lastName, position, dateOfBirth, contactInfo, hireDate, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_EMPLOYEE = 
        "UPDATE employees SET firstName = ?, lastName = ?, position = ?, dateOfBirth = ?, contactInfo = ?, hireDate = ?, salary = ? WHERE employeeID = ?";

    private static final String SQL_DELETE_USER_ROLES = 
        "DELETE ur FROM userroles ur INNER JOIN users u ON ur.userID = u.userID WHERE u.employeeID = ?";

    private static final String SQL_DELETE_USERS = 
        "DELETE FROM users WHERE employeeID = ?";

    private static final String SQL_DELETE_EMPLOYEE = 
        "DELETE FROM employees WHERE employeeID = ?";

    private static final String SQL_GET_ALL_EMPLOYEES_SIMPLE = 
        "SELECT * FROM employees";

    private final RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();

    @Override
    public List<Employee> getAllNhanVien() {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_NHAN_VIEN);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                employeeList.add(employeeRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public DefaultTableModel getAllNhanVienTableModel() {
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_NHAN_VIEN_TABLE_MODEL)) {

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"EmployeeID", "FirstName", "LastName", "Position", "DateOfBirth", "ContactInfo", "HireDate", "Salary", "Username", "RoleName"}, 0
            );

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("employeeID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String position = resultSet.getString("position");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                String contactInfo = resultSet.getString("contactInfo");
                Date hireDate = resultSet.getDate("hireDate");
                double salary = resultSet.getDouble("salary");
                String username = resultSet.getString("username");
                String roleName = resultSet.getString("roleName");

                if (username == null) username = "";
                if (roleName == null) roleName = "";

                model.addRow(new Object[]{employeeID, firstName, lastName, position, dateOfBirth, contactInfo, hireDate, salary, username, roleName});
            }
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addEmployee(Employee employee) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setDate(4, employee.getDateOfBirth());
            preparedStatement.setString(5, employee.getContactInfo());
            preparedStatement.setDate(6, employee.getHireDate());
            preparedStatement.setDouble(7, employee.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setDate(4, employee.getDateOfBirth());
            preparedStatement.setString(5, employee.getContactInfo());
            preparedStatement.setDate(6, employee.getHireDate());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setInt(8, employee.getEmployeeID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        Connection connection = null;
        PreparedStatement deleteRolesStatement = null;
        PreparedStatement deleteUsersStatement = null;
        PreparedStatement deleteEmployeeStatement = null;

        try {
            connection = MyConnection.getConnection();
            connection.setAutoCommit(false);

            deleteRolesStatement = connection.prepareStatement(SQL_DELETE_USER_ROLES);
            deleteRolesStatement.setInt(1, employeeID);
            deleteRolesStatement.executeUpdate();

            deleteUsersStatement = connection.prepareStatement(SQL_DELETE_USERS);
            deleteUsersStatement.setInt(1, employeeID);
            deleteUsersStatement.executeUpdate();

            deleteEmployeeStatement = connection.prepareStatement(SQL_DELETE_EMPLOYEE);
            deleteEmployeeStatement.setInt(1, employeeID);
            int rowsAffected = deleteEmployeeStatement.executeUpdate();

            connection.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            try {
                if (deleteRolesStatement != null) deleteRolesStatement.close();
                if (deleteUsersStatement != null) deleteUsersStatement.close();
                if (deleteEmployeeStatement != null) deleteEmployeeStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = MyConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_EMPLOYEES_SIMPLE)) {

            while (resultSet.next()) {
                employees.add(employeeRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
