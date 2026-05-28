package org.example.services;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Map;

public interface SalaryReportService {
    int getTotalQuantitySoldByDate(Date startDate, Date endDate);
    double getTotalRevenueByDate(Date startDate, Date endDate);
    Map<String, Double> getRevenueByProduct();
    DefaultTableModel getAllSalaryReport();
    DefaultTableModel getAllDoanhThu();
    DefaultTableModel getDailySalesReport(Date date);
    DefaultTableModel getMonthlySalesReport(int month, int year);
    DefaultTableModel getYearlySalesReport(int year);
    boolean deleteSalesReport(int salesReceiptID);
}
