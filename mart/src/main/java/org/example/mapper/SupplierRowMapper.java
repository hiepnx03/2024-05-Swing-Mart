package org.example.mapper;

import org.example.models.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRowMapper implements RowMapper<Supplier> {
    @Override
    public Supplier mapRow(ResultSet rs) throws SQLException {
        return Supplier.builder()
                .supplierID(rs.getInt("supplierID"))
                .supplierName(rs.getString("supplierName"))
                .contactInfo(rs.getString("contactInfo"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
