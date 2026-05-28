package org.example.mapper;

import org.example.models.SalesReceiptDetail;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesReceiptDetailRowMapper implements RowMapper<SalesReceiptDetail> {
    @Override
    public SalesReceiptDetail mapRow(ResultSet rs) throws SQLException {
        return SalesReceiptDetail.builder()
                .detailID(rs.getInt("detailID"))
                .salesReceiptID(rs.getInt("salesReceiptID"))
                .productID(rs.getInt("productID"))
                .quantity(rs.getInt("quantity"))
                .unitPrice(rs.getDouble("unitPrice"))
                .totalPrice(rs.getDouble("totalPrice"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
