package org.example.util;

import org.example.models.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRowMapper implements RowMapper<Supplier> {
    @Override
    public Supplier mapRow(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setSupplierID(rs.getInt("supplierID"));
        supplier.setSupplierName(rs.getString("supplierName"));
        supplier.setContactInfo(rs.getString("contactInfo"));
        supplier.setAddress(rs.getString("address"));
        supplier.setPhone(rs.getString("phone"));
        supplier.setEmail(rs.getString("email"));
        supplier.setCreatedBy(rs.getInt("createdBy"));
        supplier.setUpdatedBy(rs.getInt("updatedBy"));
        supplier.setUpdatedAt(rs.getDate("updatedAt"));
        return supplier;
    }
}
