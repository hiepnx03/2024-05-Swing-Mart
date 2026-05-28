package org.example.controllers;

import org.example.models.Employee;
import org.example.services.EmployeeService;
import org.example.services.impl.EmployeeServiceImpl;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController() {
        this.employeeService = new EmployeeServiceImpl();
    }

    public List<Employee> getAllNhanVien() {
        return employeeService.getAllNhanVien();
    }

    public DefaultTableModel getAllNhanVienTableModel() {
        return employeeService.getAllNhanVienTableModel();
    }

    public boolean addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    public boolean deleteEmployee(int employeeID) {
        return employeeService.deleteEmployee(employeeID);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
