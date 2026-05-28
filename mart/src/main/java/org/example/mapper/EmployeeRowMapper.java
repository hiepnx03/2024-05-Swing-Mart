package org.example.mapper;

import org.example.models.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs) throws SQLException {
        return Employee.builder()
                .employeeID(rs.getInt("employeeID"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .position(rs.getString("position"))
                .dateOfBirth(rs.getDate("dateOfBirth"))
                .contactInfo(rs.getString("contactInfo"))
                .hireDate(rs.getDate("hireDate"))
                .salary(rs.getDouble("salary"))
                .build();
    }
}
