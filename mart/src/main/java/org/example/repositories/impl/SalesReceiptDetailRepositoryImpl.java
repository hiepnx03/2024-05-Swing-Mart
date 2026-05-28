package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.repositories.SalesReceiptDetailRepository;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesReceiptDetailRepositoryImpl implements SalesReceiptDetailRepository {

    private static final String SQL_GET_ALL_SALES_RECEIPT_DETAIL = 
        "SELECT SRD.DetailID, SR.SalesReceiptID, P.ProductName, SRD.Quantity, SRD.UnitPrice, SRD.TotalPrice " +
        "FROM SalesReceiptDetails SRD " +
        "INNER JOIN SalesReceipts SR ON SRD.SalesReceiptID = SR.SalesReceiptID " +
        "INNER JOIN Products P ON SRD.ProductID = P.ProductID";

    @Override
    public DefaultTableModel getAllSalesReceiptDetail() {
        String[] columnNames = {"DetailID", "SalesReceiptID", "ProductName", "Quantity", "UnitPrice", "TotalPrice"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_SALES_RECEIPT_DETAIL);
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
