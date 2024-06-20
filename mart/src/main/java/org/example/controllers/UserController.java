package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.Employee;
import org.example.models.Role;
import org.example.models.User;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private Connection connection;

    public UserController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }
    private int loggedInUserID = -1; // Default value indicating no user is logged in
    private Map<Integer, Integer> sessionUserMap = new HashMap<>(); // Map to store user IDs in session


    // lưu ID người đăng nhập
    public int getUserID(String username) {
        int userID = -1; // Default value if user ID is not found

        // Implement your logic to retrieve the user ID from the database based on the username
        // This could involve executing a SQL query to fetch the user ID associated with the provided username
        String query = "SELECT userID FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt("userID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }

    public void setUserIDInSession(int userID) {
        loggedInUserID = userID; // Set the logged-in user ID in the controller

        // Also, store the user ID in the session map
        sessionUserMap.put(userID, userID);
    }


    // 1 Đăng nhập
    public boolean loginWithRole(String username, String password, String roleName) {
        String query = "SELECT u.*, r.* FROM users u " +
                "INNER JOIN userroles ur ON u.userID = ur.userID " +
                "INNER JOIN roles r ON ur.roleID = r.roleID " +
                "WHERE u.username = ? AND u.passwordHash = ? AND r.roleName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Đăng nhập thành công với quyền đã chọn
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Authentication failed
    }

    public Role findRoleByUserId(int userID) {
        Role role = null;
        String query = "SELECT r.* FROM roles r " +
                "INNER JOIN userroles ur ON r.roleID = ur.roleID " +
                "WHERE ur.userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setRoleID(resultSet.getInt("roleID"));
                role.setRoleName(resultSet.getString("roleName"));
                role.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public String findEmployeeByUserId(int userID) {
        String fullName = null;
        String query = "SELECT CONCAT(e.firstName, ' ', e.lastName) AS fullName " +
                "FROM employees e " +
                "INNER JOIN users u ON e.employeeID = u.employeeID " +
                "WHERE u.userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fullName = resultSet.getString("fullName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }



    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM roles";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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


    public DefaultTableModel getAllUserInfoTableModel() {
        // Chuỗi truy vấn SQL để lấy thông tin
        String query = "SELECT u.userID, u.username, u.passwordHash, u.email, e.firstName, e.lastName, e.position, r.roleName " +
                "FROM users u " +
                "INNER JOIN employees e ON u.employeeID = e.employeeID " +
                "INNER JOIN userroles ur ON u.userID = ur.userID " +
                "INNER JOIN roles r ON ur.roleID = r.roleID";

        // Kết nối CSDL và thực hiện truy vấn
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Tạo một DefaultTableModel để lưu trữ dữ liệu
            DefaultTableModel model = new DefaultTableModel(new String[]{"UserID", "Username", "PasswordHash", "Email", "FirstName", "LastName", "Position", "RoleName"}, 0);

            // Duyệt qua kết quả của truy vấn và thêm vào DefaultTableModel
            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String passwordHash = resultSet.getString("passwordHash");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String position = resultSet.getString("position");
                String roleName = resultSet.getString("roleName");

                // Thêm hàng mới vào DefaultTableModel
                model.addRow(new Object[]{userID, username, passwordHash, email, firstName, lastName, position, roleName});
            }

            return model; // Trả về DefaultTableModel đã được điền dữ liệu
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }

    public void assignUserRole(int userID, int roleID) {
        String query = "INSERT INTO userroles (userID, roleID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, roleID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findEmployeeIDByFullName(String fullName) {
        int employeeID = -1; // Giá trị mặc định nếu không tìm thấy

        String query = "SELECT employeeID FROM employees WHERE CONCAT(firstName, ' ', lastName) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeID = resultSet.getInt("employeeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeID;
    }
    public int findRoleIDByRoleName(String roleName) {
        int roleID = -1; // Giá trị mặc định nếu không tìm thấy

        String query = "SELECT roleID FROM roles WHERE roleName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roleID = resultSet.getInt("roleID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleID;
    }

    public Role findRoleByName(String roleName) {
        Role role = null;
        String query = "SELECT * FROM roles WHERE roleName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setRoleID(resultSet.getInt("roleID"));
                role.setRoleName(resultSet.getString("roleName"));
                role.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public String addUser(User user, String employeeName, int roleID) {
        String checkUsernameQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
        String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
        String checkEmployeeIDQuery = "SELECT COUNT(*) FROM users WHERE employeeID = " +
                "(SELECT employeeID FROM employees WHERE CONCAT(firstName, ' ', lastName) = ?)";
        String insertUserQuery = "INSERT INTO users (username, passwordHash, email, employeeID) " +
                "SELECT ?, ?, ?, e.employeeID " +
                "FROM employees e " +
                "WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";
        String insertUserRoleQuery = "INSERT INTO userroles (userID, roleID) " +
                "SELECT u.userID, r.roleID " +
                "FROM users u " +
                "INNER JOIN employees e ON u.employeeID = e.employeeID " +
                "INNER JOIN roles r ON r.roleID = ? " +
                "WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";

        try (PreparedStatement checkUsernameStmt = connection.prepareStatement(checkUsernameQuery);
             PreparedStatement checkEmailStmt = connection.prepareStatement(checkEmailQuery);
             PreparedStatement checkEmployeeIDStmt = connection.prepareStatement(checkEmployeeIDQuery);
             PreparedStatement insertUserStmt = connection.prepareStatement(insertUserQuery);
             PreparedStatement insertUserRoleStmt = connection.prepareStatement(insertUserRoleQuery)) {

            // Kiểm tra xem tên người dùng đã tồn tại chưa
            checkUsernameStmt.setString(1, user.getUsername());
            ResultSet resultSetUsername = checkUsernameStmt.executeQuery();
            if (resultSetUsername.next() && resultSetUsername.getInt(1) > 0) {
                return "Username already exists.";
            }

            // Kiểm tra xem email đã tồn tại chưa
            checkEmailStmt.setString(1, user.getEmail());
            ResultSet resultSetEmail = checkEmailStmt.executeQuery();
            if (resultSetEmail.next() && resultSetEmail.getInt(1) > 0) {
                return "Email already exists.";
            }

            // Kiểm tra xem employeeID đã tồn tại chưa
            checkEmployeeIDStmt.setString(1, employeeName);
            ResultSet resultSetEmployeeID = checkEmployeeIDStmt.executeQuery();
            if (resultSetEmployeeID.next() && resultSetEmployeeID.getInt(1) > 0) {
                return "Employee ID already exists.";
            }

            // Đặt các tham số cho việc thêm người dùng
            insertUserStmt.setString(1, user.getUsername());
            insertUserStmt.setString(2, user.getPasswordHash());
            insertUserStmt.setString(3, user.getEmail());
            insertUserStmt.setString(4, employeeName);

            // Thực thi câu lệnh thêm người dùng
            int rowsAffectedUser = insertUserStmt.executeUpdate();

            // Kiểm tra xem việc thêm người dùng có thành công không
            if (rowsAffectedUser > 0) {
                // Đặt các tham số cho việc thêm vai trò người dùng
                insertUserRoleStmt.setInt(1, roleID);
                insertUserRoleStmt.setString(2, employeeName);

                // Thực thi câu lệnh thêm vai trò người dùng
                int rowsAffectedUserRole = insertUserRoleStmt.executeUpdate();

                // Kiểm tra xem việc thêm vai trò người dùng có thành công không
                if (rowsAffectedUserRole > 0) {
                    return "Success";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed to add user.";
    }




    public boolean deleteUser(int userID) {
        String deleteUserRoleQuery = "DELETE FROM userroles WHERE userID = ?";
        String deleteUserQuery = "DELETE FROM users WHERE userID = ?";

        try (PreparedStatement preparedStatementUserRole = connection.prepareStatement(deleteUserRoleQuery);
             PreparedStatement preparedStatementUser = connection.prepareStatement(deleteUserQuery)) {

            // Xóa tất cả các bản ghi trong bảng userroles liên quan đến userID
            preparedStatementUserRole.setInt(1, userID);
            preparedStatementUserRole.executeUpdate();

            // Sau đó mới xóa người dùng từ bảng users
            preparedStatementUser.setInt(1, userID);
            int rowsAffected = preparedStatementUser.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateUser(User user, String employeeName, int roleID) {
        String updateUserQuery = "UPDATE users u " +
                "INNER JOIN employees e ON u.employeeID = e.employeeID " +
                "SET u.username = ?, u.passwordHash = ?, u.email = ? " +
                "WHERE CONCAT(e.firstName, ' ', e.lastName) = ?";

        String updateUserRoleQuery = "UPDATE userroles ur " +
                "INNER JOIN users u ON ur.userID = u.userID " +
                "SET ur.roleID = ? " +
                "WHERE u.username = ?";

        try (PreparedStatement preparedStatementUser = connection.prepareStatement(updateUserQuery);
             PreparedStatement preparedStatementUserRole = connection.prepareStatement(updateUserRoleQuery)) {

            // Set parameters for updating user
            preparedStatementUser.setString(1, user.getUsername());
            preparedStatementUser.setString(2, user.getPasswordHash());
            preparedStatementUser.setString(3, user.getEmail());
            preparedStatementUser.setString(4, employeeName);

            // Execute user update query
            int rowsAffectedUser = preparedStatementUser.executeUpdate();

            // Check if user update was successful
            if (rowsAffectedUser > 0) {
                // Set parameters for updating user role
                preparedStatementUserRole.setInt(1, roleID);
                preparedStatementUserRole.setString(2, user.getUsername());

                // Execute user role update query
                int rowsAffectedUserRole = preparedStatementUserRole.executeUpdate();

                // Check if user role update was successful
                if (rowsAffectedUserRole > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Employee getByEmployeeIDWithUserID(int employeeID, int userID) {
        Employee employee = null;

        // Tạo truy vấn SQL để lấy thông tin Employee dựa trên EmployeeID và UserID
        String query = "SELECT * FROM employees WHERE EmployeeID = ? AND CreatedBy = ?";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Thiết lập giá trị cho các tham số trong truy vấn
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setInt(2, userID);

            // Thực thi truy vấn và lấy kết quả
            ResultSet resultSet = preparedStatement.executeQuery();

            // Kiểm tra xem có kết quả nào không
            if (resultSet.next()) {
                // Tạo một đối tượng Employee từ dữ liệu trong kết quả
                employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                // Các trường thông tin khác của Employee
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee; // Trả về đối tượng Employee hoặc null nếu không tìm thấy
    }
    public int getEmployeeIDByUserID(int userID) {
        int employeeID = -1; // Giá trị mặc định nếu không tìm thấy

        String query = "SELECT employeeID FROM users WHERE userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeID = resultSet.getInt("employeeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeID;
    }

}
