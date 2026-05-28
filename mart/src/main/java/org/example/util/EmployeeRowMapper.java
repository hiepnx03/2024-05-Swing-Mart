package org.example.util;

import org.example.models.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("employeeID"));
        employee.setFirstName(rs.getString("firstName"));
        employee.setLastName(rs.getString("lastName"));
        employee.setPosition(rs.getString("position"));
        employee.setDateOfBirth(rs.getDate("dateOfBirth"));
        employee.setContactInfo(rs.getString("contactInfo"));
        employee.setHireDate(rs.getDate("hireDate"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}
