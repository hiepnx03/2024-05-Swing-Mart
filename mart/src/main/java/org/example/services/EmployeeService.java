package org.example.services;

import org.example.models.Employee;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllNhanVien();
    DefaultTableModel getAllNhanVienTableModel();
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeID);
    List<Employee> getAllEmployees();
}
