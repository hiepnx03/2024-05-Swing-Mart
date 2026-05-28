package org.example.services.impl;

import org.example.repositories.SalaryReportRepository;
import org.example.repositories.impl.SalaryReportRepositoryImpl;
import org.example.services.SalaryReportService;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Map;

public class SalaryReportServiceImpl implements SalaryReportService {

    private final SalaryReportRepository salaryReportRepository = new SalaryReportRepositoryImpl();

    @Override
    public int getTotalQuantitySoldByDate(Date startDate, Date endDate) {
        return salaryReportRepository.getTotalQuantitySoldByDate(startDate, endDate);
    }

    @Override
    public double getTotalRevenueByDate(Date startDate, Date endDate) {
        return salaryReportRepository.getTotalRevenueByDate(startDate, endDate);
    }

    @Override
    public Map<String, Double> getRevenueByProduct() {
        return salaryReportRepository.getRevenueByProduct();
    }

    @Override
    public DefaultTableModel getAllSalaryReport() {
        return salaryReportRepository.getAllSalaryReport();
    }

    @Override
    public DefaultTableModel getAllDoanhThu() {
        return salaryReportRepository.getAllDoanhThu();
    }

    @Override
    public DefaultTableModel getDailySalesReport(Date date) {
        return salaryReportRepository.getDailySalesReport(date);
    }

    @Override
    public DefaultTableModel getMonthlySalesReport(int month, int year) {
        return salaryReportRepository.getMonthlySalesReport(month, year);
    }

    @Override
    public DefaultTableModel getYearlySalesReport(int year) {
        return salaryReportRepository.getYearlySalesReport(year);
    }

    @Override
    public boolean deleteSalesReport(int salesReceiptID) {
        return salaryReportRepository.deleteSalesReport(salesReceiptID);
    }
}
