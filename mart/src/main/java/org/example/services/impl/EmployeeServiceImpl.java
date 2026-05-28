package org.example.services.impl;

import org.example.models.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.repositories.impl.EmployeeRepositoryImpl;
import org.example.services.EmployeeService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @Override
    public List<Employee> getAllNhanVien() {
        return employeeRepository.getAllNhanVien();
    }

    @Override
    public DefaultTableModel getAllNhanVienTableModel() {
        return employeeRepository.getAllNhanVienTableModel();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        return employeeRepository.deleteEmployee(employeeID);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}
