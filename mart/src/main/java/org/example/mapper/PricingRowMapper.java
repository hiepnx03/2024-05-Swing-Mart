package org.example.mapper;

import org.example.models.Pricing;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricingRowMapper implements RowMapper<Pricing> {
    @Override
    public Pricing mapRow(ResultSet rs) throws SQLException {
        return Pricing.builder()
                .priceID(rs.getInt("priceID"))
                .productID(rs.getInt("productID"))
                .price(rs.getDouble("price"))
                .startDate(rs.getDate("startDate"))
                .endDate(rs.getDate("endDate"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
