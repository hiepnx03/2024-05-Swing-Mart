package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.Supplier;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierController {

    private Connection connection;

    public SupplierController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    // Phương thức để lấy thông tin nhà cung cấp từ tên nhà cung cấp
    public Supplier getSupplierByName(String supplierName) {
        Supplier supplier = null;
        String query = "SELECT * FROM Suppliers WHERE SupplierName = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplierName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                supplier = new Supplier();
                supplier.setSupplierID(resultSet.getInt("supplierID"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setContactInfo(resultSet.getString("contactInfo"));
                supplier.setAddress(resultSet.getString("address"));
                supplier.setPhone(resultSet.getString("phone"));
                supplier.setEmail(resultSet.getString("email"));
                supplier.setCreatedBy(resultSet.getInt("createdBy"));
                supplier.setUpdatedBy(resultSet.getInt("updatedBy"));
                supplier.setUpdatedAt(resultSet.getDate("updatedAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }


    public DefaultTableModel getAllSuppliers() {
        String[] columnNames = {
                "SupplierID", "SupplierName", "ContactInfo", "Address",
                "Phone", "Email", "CreatedBy", "UpdatedBy", "UpdatedAt"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT * FROM Suppliers";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int supplierID = resultSet.getInt("SupplierID");
                String supplierName = resultSet.getString("SupplierName");
                String contactInfo = resultSet.getString("ContactInfo");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                int createdBy = resultSet.getInt("CreatedBy");
                int updatedBy = resultSet.getInt("UpdatedBy");
                java.sql.Timestamp updatedAt = resultSet.getTimestamp("UpdatedAt");

                model.addRow(new Object[]{
                        supplierID, supplierName, contactInfo, address,
                        phone, email, createdBy, updatedBy, updatedAt
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO Suppliers (SupplierName, ContactInfo, Address, Phone, Email, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getContactInfo());
            preparedStatement.setString(3, supplier.getAddress());
            preparedStatement.setString(4, supplier.getPhone());
            preparedStatement.setString(5, supplier.getEmail());
            preparedStatement.setInt(6, supplier.getCreatedBy());
            preparedStatement.setInt(7, supplier.getUpdatedBy());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE Suppliers SET SupplierName = ?, ContactInfo = ?, Address = ?, Phone = ?, Email = ?, UpdatedBy = ? WHERE SupplierID = ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getContactInfo());
            preparedStatement.setString(3, supplier.getAddress());
            preparedStatement.setString(4, supplier.getPhone());
            preparedStatement.setString(5, supplier.getEmail());
            preparedStatement.setInt(6, supplier.getUpdatedBy());
            preparedStatement.setInt(7, supplier.getSupplierID());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(int supplierID) {
        String countProductsSql = "SELECT COUNT(*) FROM Products WHERE SupplierID = ?";
        String deletePricingSql = "DELETE FROM Pricing WHERE ProductID IN (SELECT ProductID FROM Products WHERE SupplierID = ?)";
        String deleteProductsSql = "DELETE FROM Products WHERE SupplierID = ?";
        String deleteSupplierSql = "DELETE FROM Suppliers WHERE SupplierID = ?";
        try (Connection conn = MyConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement countProductsStmt = conn.prepareStatement(countProductsSql);
                 PreparedStatement deletePricingStmt = conn.prepareStatement(deletePricingSql);
                 PreparedStatement deleteProductsStmt = conn.prepareStatement(deleteProductsSql);
                 PreparedStatement deleteSupplierStmt = conn.prepareStatement(deleteSupplierSql)) {

                // Check if there are any products associated with the supplier ID
                countProductsStmt.setInt(1, supplierID);
                ResultSet resultSet = countProductsStmt.executeQuery();
                resultSet.next();
                int productCount = resultSet.getInt(1);
                if (productCount > 0) {
                    // If there are products, throw an exception or handle it accordingly
                    throw new RuntimeException("Cannot delete supplier because it has associated products.");
                }

                deletePricingStmt.setInt(1, supplierID);
                int deletedPricingRows = deletePricingStmt.executeUpdate();

                deleteProductsStmt.setInt(1, supplierID);
                int deletedProductRows = deleteProductsStmt.executeUpdate();

                deleteSupplierStmt.setInt(1, supplierID);
                boolean success = deleteSupplierStmt.executeUpdate() > 0;

                conn.commit();
                return success;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                // You can log the exception or throw a custom exception here
                throw new RuntimeException("Error deleting supplier: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // You can log the exception or throw a custom exception here
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }




}
