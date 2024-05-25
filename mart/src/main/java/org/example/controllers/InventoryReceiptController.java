package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.InventoryReceipt;
import org.example.models.Order;
import org.example.models.Supplier;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class InventoryReceiptController {
    private Connection connection;

    public InventoryReceiptController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    public DefaultTableModel getAllInventoryReceipts() {
        String query = "SELECT ir.ReceiptID, ir.OrderID, ir.ReceiptDate, ir.TotalAmount, ir.CreatedBy, ir.UpdatedBy, ir.UpdatedAt, " +
                "o.OrderID AS OrderID, o.SupplierID AS SupplierID, o.OrderDate, o.Status, o.TotalAmount AS OrderTotalAmount, o.CreatedBy AS OrderCreatedBy, o.UpdatedBy AS OrderUpdatedBy, o.UpdatedAt AS OrderUpdatedAt, " +
                "s.SupplierID AS SupplierID, s.SupplierName, s.ContactInfo, s.Address, s.Phone, s.Email, s.CreatedBy AS SupplierCreatedBy, s.UpdatedBy AS SupplierUpdatedBy, s.UpdatedAt AS SupplierUpdatedAt " +
                "FROM InventoryReceipts ir " +
                "INNER JOIN Orders o ON ir.OrderID = o.OrderID " +
                "INNER JOIN Suppliers s ON o.SupplierID = s.SupplierID";

        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ReceiptID", "OrderID", "ReceiptDate", "TotalAmount", "CreatedBy", "UpdatedBy", "UpdatedAt",
                            "OrderID", "SupplierID", "OrderDate", "Status", "OrderTotalAmount", "OrderCreatedBy", "OrderUpdatedBy", "OrderUpdatedAt",
                            "SupplierID", "SupplierName", "ContactInfo", "Address", "Phone", "Email", "SupplierCreatedBy", "SupplierUpdatedBy", "SupplierUpdatedAt"}, 0);

            while (resultSet.next()) {
                int receiptID = resultSet.getInt("ReceiptID");
                int orderID = resultSet.getInt("OrderID");
                Date receiptDate = resultSet.getDate("ReceiptDate");
                double totalAmount = resultSet.getDouble("TotalAmount");
                int createdBy = resultSet.getInt("CreatedBy");
                int updatedBy = resultSet.getInt("UpdatedBy");
                Date updatedAt = resultSet.getDate("UpdatedAt");

                int orderIDFromOrder = resultSet.getInt("OrderID");
                int supplierID = resultSet.getInt("SupplierID");
                Date orderDate = resultSet.getDate("OrderDate");
                String status = resultSet.getString("Status");
                double orderTotalAmount = resultSet.getDouble("OrderTotalAmount");
                int orderCreatedBy = resultSet.getInt("OrderCreatedBy");
                int orderUpdatedBy = resultSet.getInt("OrderUpdatedBy");
                Date orderUpdatedAt = resultSet.getDate("OrderUpdatedAt");

                int supplierIDFromSupplier = resultSet.getInt("SupplierID");
                String supplierName = resultSet.getString("SupplierName");
                String contactInfo = resultSet.getString("ContactInfo");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                int supplierCreatedBy = resultSet.getInt("SupplierCreatedBy");
                int supplierUpdatedBy = resultSet.getInt("SupplierUpdatedBy");
                Date supplierUpdatedAt = resultSet.getDate("SupplierUpdatedAt");

                model.addRow(new Object[]{receiptID, orderID, receiptDate, totalAmount, createdBy, updatedBy, updatedAt,
                        orderIDFromOrder, supplierID, orderDate, status, orderTotalAmount, orderCreatedBy, orderUpdatedBy, orderUpdatedAt,
                        supplierIDFromSupplier, supplierName, contactInfo, address, phone, email, supplierCreatedBy, supplierUpdatedBy, supplierUpdatedAt});
            }

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
