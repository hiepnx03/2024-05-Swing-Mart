package org.example.mapper;

import org.example.models.Debt;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtRowMapper implements RowMapper<Debt> {
    @Override
    public Debt mapRow(ResultSet rs) throws SQLException {
        return Debt.builder()
                .debtID(rs.getInt("debtID"))
                .supplierID(rs.getInt("supplierID"))
                .amount(rs.getDouble("amount"))
                .dueDate(rs.getDate("dueDate"))
                .status(rs.getString("status"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
