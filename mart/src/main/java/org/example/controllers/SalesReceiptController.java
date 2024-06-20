package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.SalesReceipt;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SalesReceiptController {

    private Connection connection;

    public SalesReceiptController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    public DefaultTableModel getAllPhieuBanHang() {
        String query = "SELECT sr.SalesReceiptID, sr.EmployeeID, sr.SaleDate, sr.TotalAmount, sr.CustomerName, sr.PaymentMethod, sr.CreatedBy, sr.UpdatedBy, sr.UpdatedAt, " +
                "e.EmployeeID AS EmployeeID, e.FirstName, e.LastName, e.Position, e.DateOfBirth, e.ContactInfo, e.HireDate, e.Salary, e.CreatedBy AS EmployeeCreatedBy, e.UpdatedBy AS EmployeeUpdatedBy, e.UpdatedAt AS EmployeeUpdatedAt " +
                "FROM SalesReceipts sr " +
                "INNER JOIN Employees e ON sr.EmployeeID = e.EmployeeID";

        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"SalesReceiptID", "EmployeeID", "SaleDate", "TotalAmount", "CustomerName", "PaymentMethod", "CreatedBy", "UpdatedBy", "UpdatedAt",
                            "EmployeeID", "FirstName", "LastName", "Position", "DateOfBirth", "ContactInfo", "HireDate", "Salary", "EmployeeCreatedBy", "EmployeeUpdatedBy", "EmployeeUpdatedAt"}, 0);

            while (resultSet.next()) {
                int salesReceiptID = resultSet.getInt("SalesReceiptID");
                int employeeID = resultSet.getInt("EmployeeID");
                Date saleDate = resultSet.getDate("SaleDate");
                double totalAmount = resultSet.getDouble("TotalAmount");
                String customerName = resultSet.getString("CustomerName");
                String paymentMethod = resultSet.getString("PaymentMethod");
                int createdBy = resultSet.getInt("CreatedBy");
                int updatedBy = resultSet.getInt("UpdatedBy");
                Date updatedAt = resultSet.getDate("UpdatedAt");

                int employeeIDFromEmployee = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String position = resultSet.getString("Position");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String contactInfo = resultSet.getString("ContactInfo");
                Date hireDate = resultSet.getDate("HireDate");
                double salary = resultSet.getDouble("Salary");
                int employeeCreatedBy = resultSet.getInt("EmployeeCreatedBy");
                int employeeUpdatedBy = resultSet.getInt("EmployeeUpdatedBy");
                Date employeeUpdatedAt = resultSet.getDate("EmployeeUpdatedAt");

                model.addRow(new Object[]{salesReceiptID, employeeID, saleDate, totalAmount, customerName, paymentMethod, createdBy, updatedBy, updatedAt,
                        employeeIDFromEmployee, firstName, lastName, position, dateOfBirth, contactInfo, hireDate, salary, employeeCreatedBy, employeeUpdatedBy, employeeUpdatedAt});
            }

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }





}
