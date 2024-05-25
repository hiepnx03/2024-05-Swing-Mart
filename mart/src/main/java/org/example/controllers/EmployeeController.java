package org.example.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.connect.MyConnection;
import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;

import javax.swing.table.DefaultTableModel;

public class EmployeeController {
    private Connection connection;

    public EmployeeController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }
    public List<Employee> getAllNhanVien() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT e.*, r.roleID, r.roleName " +
                "FROM employees e " +
                "LEFT JOIN users u ON e.employeeID = u.employeeID " +
                "LEFT JOIN userroles ur ON u.userID = ur.userID " +
                "LEFT JOIN roles r ON ur.roleID = r.roleID";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                // Lấy dữ liệu từ ResultSet và thiết lập cho đối tượng Employee
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setPosition(resultSet.getString("position"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                employee.setContactInfo(resultSet.getString("contactInfo"));
                employee.setHireDate(resultSet.getDate("hireDate"));
                employee.setSalary(resultSet.getDouble("salary"));

                // Tạo đối tượng Role từ dữ liệu cột roles
                Role role = new Role();
                role.setRoleID(resultSet.getInt("roleID"));
                role.setRoleName(resultSet.getString("roleName"));
                // Thêm thông tin về vai trò vào danh sách các vai trò của nhân viên
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }


    public DefaultTableModel getAllNhanVienTableModel() {
        // Chuỗi truy vấn SQL để lấy thông tin với LEFT JOIN
        String query = "SELECT e.employeeID, e.firstName, e.lastName, e.position, e.dateOfBirth, e.contactInfo, e.hireDate, e.salary, u.username, r.roleName " +
                "FROM employees e " +
                "LEFT JOIN users u ON e.employeeID = u.employeeID " +
                "LEFT JOIN userroles ur ON u.userID = ur.userID " +
                "LEFT JOIN roles r ON ur.roleID = r.roleID";

        // Kết nối CSDL và thực hiện truy vấn
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Tạo một DefaultTableModel để lưu trữ dữ liệu
            DefaultTableModel model = new DefaultTableModel(new String[]{"EmployeeID", "FirstName", "LastName", "Position", "DateOfBirth", "ContactInfo", "HireDate", "Salary", "Username", "RoleName"}, 0);

            // Duyệt qua kết quả của truy vấn và thêm vào DefaultTableModel
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

                // Xử lý các giá trị null
                if (username == null) {
                    username = "";
                }
                if (roleName == null) {
                    roleName = "";
                }

                // Thêm hàng mới vào DefaultTableModel
                model.addRow(new Object[]{employeeID, firstName, lastName, position, dateOfBirth, contactInfo, hireDate, salary, username, roleName});
            }

            return model; // Trả về DefaultTableModel đã được điền dữ liệu
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }





    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employees (firstName, lastName, position, dateOfBirth, contactInfo, hireDate, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE employees SET firstName = ?, lastName = ?, position = ?, dateOfBirth = ?, contactInfo = ?, hireDate = ?, salary = ? WHERE employeeID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    public boolean deleteEmployee(int employeeID) {
        Connection connection = null;
        PreparedStatement deleteRolesStatement = null;
        PreparedStatement deleteUsersStatement = null;
        PreparedStatement deleteEmployeeStatement = null;

        try {
            connection = MyConnection.getConnection();
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Xóa các vai trò của người dùng trong bảng userroles
            String deleteRolesQuery = "DELETE ur FROM userroles ur " +
                    "INNER JOIN users u ON ur.userID = u.userID " +
                    "WHERE u.employeeID = ?";
            deleteRolesStatement = connection.prepareStatement(deleteRolesQuery);
            deleteRolesStatement.setInt(1, employeeID);
            deleteRolesStatement.executeUpdate();

            // Xóa tài khoản người dùng trong bảng users
            String deleteUsersQuery = "DELETE FROM users WHERE employeeID = ?";
            deleteUsersStatement = connection.prepareStatement(deleteUsersQuery);
            deleteUsersStatement.setInt(1, employeeID);
            deleteUsersStatement.executeUpdate();

            // Xóa thông tin nhân viên trong bảng employees
            String deleteEmployeeQuery = "DELETE FROM employees WHERE employeeID = ?";
            deleteEmployeeStatement = connection.prepareStatement(deleteEmployeeQuery);
            deleteEmployeeStatement.setInt(1, employeeID);
            int rowsAffected = deleteEmployeeStatement.executeUpdate();

            connection.commit(); // Commit transaction
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction nếu có lỗi xảy ra
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            // Đóng các PreparedStatement và Connection
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


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees"; // Assuming your table name is "employees"

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                // Set other properties as needed
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

}
