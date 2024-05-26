package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.SalesReceiptDetail;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class SalesReceiptDetailController {

    private Connection connection;

    public SalesReceiptDetailController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }


    public DefaultTableModel getAllSalesReceiptDetail() {
        String[] columnNames = {"DetailID", "SalesReceiptID", "ProductName", "Quantity", "UnitPrice", "TotalPrice"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT SRD.DetailID, SR.SalesReceiptID, P.ProductName, SRD.Quantity, SRD.UnitPrice, SRD.TotalPrice " +
                "FROM SalesReceiptDetails SRD " +
                "INNER JOIN SalesReceipts SR ON SRD.SalesReceiptID = SR.SalesReceiptID " +
                "INNER JOIN Products P ON SRD.ProductID = P.ProductID";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("DetailID"),
                        resultSet.getInt("SalesReceiptID"),
                        resultSet.getString("ProductName"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getDouble("TotalPrice")
                };
                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

}
