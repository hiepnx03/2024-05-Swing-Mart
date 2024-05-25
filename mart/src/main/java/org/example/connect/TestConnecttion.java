
package org.example.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestConnecttion {

    public static void main(String[] args) {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/mart";
            String userName = "root";
            String password = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, userName, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
                // tạo đối tượng Statement
                Statement statement = conn.createStatement();
                // truy vấn
                String select_User = "select * from Users";
                ResultSet resultSet = statement.executeQuery(select_User);
                // xử lý kết quả
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("UserId");
                    String username1 = resultSet.getString("Username");
                    String password1 = resultSet.getString("PasswordHash");
                    System.out.println("Id: "+id);
                    System.out.println("Username: "+username1);
                    System.out.println("Password: "+password1);
                }
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
