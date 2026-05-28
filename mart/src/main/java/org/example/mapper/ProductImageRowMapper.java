package org.example.mapper;

import org.example.models.ProductImage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductImageRowMapper implements RowMapper<ProductImage> {
    @Override
    public ProductImage mapRow(ResultSet rs) throws SQLException {
        return ProductImage.builder()
                .imageID(rs.getInt("imageID"))
                .productID(rs.getInt("productID"))
                .imageUrl(rs.getString("imageUrl"))
                .createdAt(rs.getDate("createdAt"))
                .build();
    }
}
