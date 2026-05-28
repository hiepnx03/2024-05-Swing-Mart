package org.example.mapper;

import org.example.models.SalesReceipt;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesReceiptRowMapper implements RowMapper<SalesReceipt> {
    @Override
    public SalesReceipt mapRow(ResultSet rs) throws SQLException {
        return SalesReceipt.builder()
                .salesReceiptID(rs.getInt("salesReceiptID"))
                .employeeID(rs.getInt("employeeID"))
                .saleDate(rs.getDate("saleDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .customerName(rs.getString("customerName"))
                .paymentMethod(rs.getString("paymentMethod"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
