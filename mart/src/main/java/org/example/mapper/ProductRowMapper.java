package org.example.mapper;

import org.example.models.Product;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs) throws SQLException {
        return Product.builder()
                .productID(rs.getInt("productID"))
                .productName(rs.getString("productName"))
                .category(rs.getString("category"))
                .stockQuantity(rs.getInt("stockQuantity"))
                .unitPrice(rs.getDouble("unitPrice"))
                .supplierID(rs.getInt("supplierID"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getTimestamp("updatedAt") != null ? new java.util.Date(rs.getTimestamp("updatedAt").getTime()) : null)
                .build();
    }
}
