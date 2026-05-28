package org.example.mapper;

import org.example.models.InventoryReceipt;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryReceiptRowMapper implements RowMapper<InventoryReceipt> {
    @Override
    public InventoryReceipt mapRow(ResultSet rs) throws SQLException {
        return InventoryReceipt.builder()
                .receiptID(rs.getInt("receiptID"))
                .orderID(rs.getInt("orderID"))
                .receiptDate(rs.getDate("receiptDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
