package org.example.mapper;

import org.example.models.SalaryReport;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryReportRowMapper implements RowMapper<SalaryReport> {
    @Override
    public SalaryReport mapRow(ResultSet rs) throws SQLException {
        return SalaryReport.builder()
                .reportID(rs.getInt("reportID"))
                .employeeID(rs.getInt("employeeID"))
                .reportDate(rs.getDate("reportDate"))
                .period(rs.getString("period"))
                .totalSalary(rs.getDouble("totalSalary"))
                .createdBy(rs.getInt("createdBy"))
                .updatedBy(rs.getInt("updatedBy"))
                .updatedAt(rs.getDate("updatedAt"))
                .build();
    }
}
