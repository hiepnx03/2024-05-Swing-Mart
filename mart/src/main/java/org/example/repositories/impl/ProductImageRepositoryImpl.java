package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.repositories.ProductImageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductImageRepositoryImpl implements ProductImageRepository {

    private static final String SQL_CHECK_FILE_NAME_EXISTS = 
        "SELECT COUNT(*) AS count FROM ProductImages WHERE ImageUrl = ?";

    @Override
    public boolean checkFileNameExists(String fileName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL_CHECK_FILE_NAME_EXISTS)) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
