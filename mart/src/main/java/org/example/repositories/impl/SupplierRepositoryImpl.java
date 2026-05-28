package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.models.Supplier;
import org.example.repositories.SupplierRepository;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl implements SupplierRepository {

    private static final String SQL_GET_SUPPLIER_BY_NAME = "SELECT * FROM Suppliers WHERE SupplierName = ?";
    private static final String SQL_GET_ALL_SUPPLIERS = "SELECT * FROM Suppliers";
    private static final String SQL_ADD_SUPPLIER = 
        "INSERT INTO Suppliers (SupplierName, ContactInfo, Address, Phone, Email, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_SUPPLIER = 
        "UPDATE Suppliers SET SupplierName = ?, ContactInfo = ?, Address = ?, Phone = ?, Email = ?, UpdatedBy = ? WHERE SupplierID = ?";
    
    private static final String SQL_COUNT_PRODUCTS = "SELECT COUNT(*) FROM Products WHERE SupplierID = ?";
    private static final String SQL_DELETE_PRICING = "DELETE FROM Pricing WHERE ProductID IN (SELECT ProductID FROM Products WHERE SupplierID = ?)";
    private static final String SQL_DELETE_PRODUCTS = "DELETE FROM Products WHERE SupplierID = ?";
    private static final String SQL_DELETE_SUPPLIER = "DELETE FROM Suppliers WHERE SupplierID = ?";

    @Override
    public Supplier getSupplierByName(String supplierName) {
        Supplier supplier = null;
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL_GET_SUPPLIER_BY_NAME)) {
            statement.setString(1, supplierName);
            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public DefaultTableModel getAllSuppliers() {
        String[] columnNames = {
                "SupplierID", "SupplierName", "ContactInfo", "Address",
                "Phone", "Email", "CreatedBy", "UpdatedBy", "UpdatedAt"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_SUPPLIERS);
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

    @Override
    public boolean addSupplier(Supplier supplier) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_SUPPLIER)) {
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

    @Override
    public boolean updateSupplier(Supplier supplier) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_SUPPLIER)) {
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getContactInfo());
            preparedStatement.setString(3, supplier.getAddress());
            preparedStatement.setString(4, supplier.getPhone());
            preparedStatement.setString(5, supplier.getEmail());
            preparedStatement.setInt(6, supplier.getUpdatedBy());
            preparedStatement.setInt(7, supplier.getSupplierID());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(int supplierID) {
        try (Connection conn = MyConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement countProductsStmt = conn.prepareStatement(SQL_COUNT_PRODUCTS);
                 PreparedStatement deletePricingStmt = conn.prepareStatement(SQL_DELETE_PRICING);
                 PreparedStatement deleteProductsStmt = conn.prepareStatement(SQL_DELETE_PRODUCTS);
                 PreparedStatement deleteSupplierStmt = conn.prepareStatement(SQL_DELETE_SUPPLIER)) {

                countProductsStmt.setInt(1, supplierID);
                try (ResultSet resultSet = countProductsStmt.executeQuery()) {
                    resultSet.next();
                    int productCount = resultSet.getInt(1);
                    if (productCount > 0) {
                        throw new RuntimeException("Cannot delete supplier because it has associated products.");
                    }
                }

                deletePricingStmt.setInt(1, supplierID);
                deletePricingStmt.executeUpdate();

                deleteProductsStmt.setInt(1, supplierID);
                deleteProductsStmt.executeUpdate();

                deleteSupplierStmt.setInt(1, supplierID);
                boolean success = deleteSupplierStmt.executeUpdate() > 0;

                conn.commit();
                return success;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error deleting supplier: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }
}
