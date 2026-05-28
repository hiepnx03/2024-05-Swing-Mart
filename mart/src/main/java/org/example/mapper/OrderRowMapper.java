package org.example.mapper;

import org.example.models.Order;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs) throws SQLException {
        return Order.builder()
                .orderID(rs.getInt("orderID"))
                .supplierID(rs.getInt("supplierID"))
                .orderDate(rs.getDate("orderDate"))
                .status(rs.getString("status"))
                .totalAmount(rs.getDouble("totalAmount"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
