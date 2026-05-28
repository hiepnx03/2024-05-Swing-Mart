package org.example.controllers;

import org.example.services.SalaryReportService;
import org.example.services.impl.SalaryReportServiceImpl;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Map;

public class SalaryReportController {

    private final SalaryReportService salaryReportService;

    public SalaryReportController() {
        this.salaryReportService = new SalaryReportServiceImpl();
    }

    public int getTotalQuantitySoldByDate(Date startDate, Date endDate) {
        return salaryReportService.getTotalQuantitySoldByDate(startDate, endDate);
    }

    public double getTotalRevenueByDate(Date startDate, Date endDate) {
        return salaryReportService.getTotalRevenueByDate(startDate, endDate);
    }

    public Map<String, Double> getRevenueByProduct() {
        return salaryReportService.getRevenueByProduct();
    }

    public DefaultTableModel getAllSalaryReport() {
        return salaryReportService.getAllSalaryReport();
    }

    public DefaultTableModel getAllDoanhThu() {
        return salaryReportService.getAllDoanhThu();
    }

    public DefaultTableModel getDailySalesReport(Date date) {
        return salaryReportService.getDailySalesReport(date);
    }

    public DefaultTableModel getMonthlySalesReport(int month, int year) {
        return salaryReportService.getMonthlySalesReport(month, year);
    }

    public DefaultTableModel getYearlySalesReport(int year) {
        return salaryReportService.getYearlySalesReport(year);
    }

    public boolean deleteSalesReport(int salesReceiptID) {
        return salaryReportService.deleteSalesReport(salesReceiptID);
    }
}
