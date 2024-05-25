package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.Product;
import org.example.models.ProductImage;
import org.example.models.Supplier;
import org.example.models.User;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductController {

    private Connection connection;

    public ProductController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    public DefaultTableModel getAllProducts() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Mã nhà cung cấp", "Tạo bởi", "Cập nhật bởi", "Thời gian cập nhật"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT * FROM Products";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String category = resultSet.getString("Category");
                int stockQuantity = resultSet.getInt("StockQuantity");
                double unitPrice = resultSet.getDouble("UnitPrice");
                int supplierID = resultSet.getInt("SupplierID");
                int createdBy = resultSet.getInt("CreatedBy");
                int updatedBy = resultSet.getInt("UpdatedBy");
                Date updatedAt = resultSet.getTimestamp("UpdatedAt");

                model.addRow(new Object[]{productID, productName, category, stockQuantity, unitPrice, supplierID, createdBy, updatedBy, updatedAt});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }


    public DefaultTableModel getAllProductDetails() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Nhà cung cấp", "Tạo bởi", "Người tạo", "Cập nhật bởi", "Người cập nhật", "Thời gian cập nhật", "Hình ảnh"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT " +
                "p.ProductID AS 'Mã sản phẩm', " +
                "p.ProductName AS 'Tên sản phẩm', " +
                "p.Category AS 'Danh mục', " +
                "p.StockQuantity AS 'Số lượng tồn kho', " +
                "p.UnitPrice AS 'Đơn giá', " +
                "s.SupplierName AS 'Nhà cung cấp', " +
                "p.CreatedBy AS 'Tạo bởi', " +
                "uc.Username AS 'Người tạo', " +
                "p.UpdatedBy AS 'Cập nhật bởi', " +
                "uu.Username AS 'Người cập nhật', " +
                "p.UpdatedAt AS 'Thời gian cập nhật', " +
                "pi.ImageUrl AS 'Hình ảnh' " +
                "FROM Products p " +
                "LEFT JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
                "LEFT JOIN Users uc ON p.CreatedBy = uc.UserID " +
                "LEFT JOIN Users uu ON p.UpdatedBy = uu.UserID " +
                "LEFT JOIN ProductImages pi ON p.ProductID = pi.ProductID";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productID = resultSet.getInt("Mã sản phẩm");
                String productName = resultSet.getString("Tên sản phẩm");
                String category = resultSet.getString("Danh mục");
                int stockQuantity = resultSet.getInt("Số lượng tồn kho");
                double unitPrice = resultSet.getDouble("Đơn giá");
                String supplierName = resultSet.getString("Nhà cung cấp");
                int createdBy = resultSet.getInt("Tạo bởi");
                String creatorUsername = resultSet.getString("Người tạo");
                int updatedBy = resultSet.getInt("Cập nhật bởi");
                String updaterUsername = resultSet.getString("Người cập nhật");
                java.util.Date updatedAt = resultSet.getTimestamp("Thời gian cập nhật");
                String imageUrl = resultSet.getString("Hình ảnh");

                model.addRow(new Object[]{productID, productName, category, stockQuantity, unitPrice, supplierName, createdBy, creatorUsername, updatedBy, updaterUsername, updatedAt, imageUrl});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }


    public boolean addProduct(Product product, ProductImage productImage, int supplierID) {
        String insertProductQuery = "INSERT INTO Products (ProductName, Category, StockQuantity, UnitPrice, SupplierID, UpdatedBy, UpdatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertProductImageQuery = "INSERT INTO ProductImages (ProductID, ImageUrl, CreatedAt) VALUES (?, ?, ?)";

        try {
            // Thêm sản phẩm
            PreparedStatement insertProductStmt = connection.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS);
            insertProductStmt.setString(1, product.getProductName());
            insertProductStmt.setString(2, product.getCategory());
            insertProductStmt.setInt(3, product.getStockQuantity());
            insertProductStmt.setDouble(4, product.getUnitPrice());
            insertProductStmt.setInt(5, supplierID);
            insertProductStmt.setInt(6, product.getUpdatedBy());
            insertProductStmt.setTimestamp(7, new Timestamp(new Date().getTime()));
            insertProductStmt.executeUpdate();

            // Lấy ID của sản phẩm vừa thêm
            ResultSet generatedKeys = insertProductStmt.getGeneratedKeys();
            int productID = 0;
            if (generatedKeys.next()) {
                productID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating product failed, no ID obtained.");
            }

            // Thêm hình ảnh sản phẩm
            PreparedStatement insertProductImageStmt = connection.prepareStatement(insertProductImageQuery);
            insertProductImageStmt.setInt(1, productID);
            insertProductImageStmt.setString(2, productImage.getImageUrl());
            insertProductImageStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertProductImageStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }






    private int getInsertedSupplierID() throws SQLException {
        // Lấy ID của nhà cung cấp vừa được thêm vào
        String selectLastSupplierIDQuery = "SELECT LAST_INSERT_ID()";
        PreparedStatement selectLastSupplierIDStmt = connection.prepareStatement(selectLastSupplierIDQuery);
        ResultSet resultSet = selectLastSupplierIDStmt.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private int getInsertedProductID() throws SQLException {
        // Lấy ID của sản phẩm vừa được thêm vào
        String selectLastProductIDQuery = "SELECT LAST_INSERT_ID()";
        PreparedStatement selectLastProductIDStmt = connection.prepareStatement(selectLastProductIDQuery);
        ResultSet resultSet = selectLastProductIDStmt.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public boolean updateProduct(Product product) {
        String updateProductQuery = "UPDATE Products SET ProductName = ?, Category = ?, StockQuantity = ?, UnitPrice = ?, UpdatedBy = ?, UpdatedAt = ? WHERE ProductID = ?";

        try (PreparedStatement updateProductStmt = connection.prepareStatement(updateProductQuery)) {
            updateProductStmt.setString(1, product.getProductName());
            updateProductStmt.setString(2, product.getCategory());
            updateProductStmt.setInt(3, product.getStockQuantity());
            updateProductStmt.setDouble(4, product.getUnitPrice());
            updateProductStmt.setInt(5, product.getUpdatedBy());
            updateProductStmt.setTimestamp(6, new Timestamp(new Date().getTime()));
            updateProductStmt.setInt(7, product.getProductID());

            int rowsAffected = updateProductStmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(int productID) {
        String deleteProductQuery = "DELETE FROM Products WHERE ProductID = ?";

        try (PreparedStatement deleteProductStmt = connection.prepareStatement(deleteProductQuery)) {
            deleteProductStmt.setInt(1, productID);

            int rowsAffected = deleteProductStmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
