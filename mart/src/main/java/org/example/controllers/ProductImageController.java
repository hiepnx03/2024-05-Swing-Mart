package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.ProductImage;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProductImageController {

    private Connection connection;

    public ProductImageController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    public boolean checkFileNameExists(String fileName) {
        try {
            // Prepare the SQL statement to check if the file name exists
            String query = "SELECT COUNT(*) AS count FROM ProductImages WHERE ImageUrl = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the file name parameter in the prepared statement
                statement.setString(1, fileName);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if any row exists with the given file name
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
