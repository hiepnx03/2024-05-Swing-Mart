package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.SalaryReport;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SalaryReportController {
    private Connection connection;


    public SalaryReportController() {
        this.connection = MyConnection.getConnection();
    }

    public DefaultTableModel getAllSalaryReport() {
        String query = "SELECT sr.ReportID, sr.EmployeeID, sr.ReportDate, sr.Period, sr.TotalSalary, e.FirstName, e.LastName, COUNT(a.AttendanceID) AS AttendanceCount " +
                "FROM SalaryReports sr " +
                "INNER JOIN Employees e ON sr.EmployeeID = e.EmployeeID " +
                "INNER JOIN Attendance a ON sr.EmployeeID = a.EmployeeID " +
                "GROUP BY sr.ReportID, sr.EmployeeID, sr.ReportDate, sr.Period, sr.TotalSalary, e.FirstName, e.LastName";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ReportID", "EmployeeID", "ReportDate", "Period", "TotalSalary", "FirstName", "LastName", "AttendanceCount"}, 0);

            while (resultSet.next()) {
                int reportID = resultSet.getInt("ReportID");
                int employeeID = resultSet.getInt("EmployeeID");
                Date reportDate = resultSet.getDate("ReportDate");
                String period = resultSet.getString("Period");
                double totalSalary = resultSet.getDouble("TotalSalary");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int attendanceCount = resultSet.getInt("AttendanceCount");

                model.addRow(new Object[]{reportID, employeeID, reportDate, period, totalSalary, firstName, lastName, attendanceCount});
            }

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // báo cáo doanh thu

    public DefaultTableModel getAllDoanhThu() {
        String query = "SELECT sr.SalesReceiptID, sr.SaleDate, sr.TotalAmount, sr.CustomerName, sr.PaymentMethod, e.FirstName, e.LastName " +
                "FROM SalesReceipts sr " +
                "INNER JOIN Employees e ON sr.EmployeeID = e.EmployeeID";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"SalesReceiptID", "SaleDate", "TotalAmount", "CustomerName", "PaymentMethod", "FirstName", "LastName"}, 0);

            while (resultSet.next()) {
                int salesReceiptID = resultSet.getInt("SalesReceiptID");
                Date saleDate = resultSet.getDate("SaleDate");
                double totalAmount = resultSet.getDouble("TotalAmount");
                String customerName = resultSet.getString("CustomerName");
                String paymentMethod = resultSet.getString("PaymentMethod");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                model.addRow(new Object[]{salesReceiptID, saleDate, totalAmount, customerName, paymentMethod, firstName, lastName});
            }

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Hàm để lấy báo cáo doanh thu theo ngày
    public DefaultTableModel getDailySalesReport(Date date) {
        String query = "SELECT SaleDate, SUM(TotalAmount) AS TotalSales " +
                "FROM SalesReceipts " +
                "WHERE SaleDate = '" + date + "' " +
                "GROUP BY SaleDate";

        return executeQueryAndGenerateTableModel(query);
    }

    // Hàm để lấy báo cáo doanh thu theo tháng
    public DefaultTableModel getMonthlySalesReport(int month, int year) {
        String query = "SELECT YEAR(SaleDate) AS SaleYear, MONTH(SaleDate) AS SaleMonth, SUM(TotalAmount) AS TotalSales " +
                "FROM SalesReceipts " +
                "WHERE MONTH(SaleDate) = " + month + " AND YEAR(SaleDate) = " + year + " " +
                "GROUP BY YEAR(SaleDate), MONTH(SaleDate)";

        return executeQueryAndGenerateTableModel(query);
    }

    // Hàm để lấy báo cáo doanh thu theo năm
    public DefaultTableModel getYearlySalesReport(int year) {
        String query = "SELECT YEAR(SaleDate) AS SaleYear, SUM(TotalAmount) AS TotalSales " +
                "FROM SalesReceipts " +
                "WHERE YEAR(SaleDate) = " + year + " " +
                "GROUP BY YEAR(SaleDate)";

        return executeQueryAndGenerateTableModel(query);
    }

    // Hàm thực hiện truy vấn và tạo DefaultTableModel từ kết quả truy vấn
    private DefaultTableModel executeQueryAndGenerateTableModel(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"SaleDate", "TotalSales"}, 0);

            while (resultSet.next()) {
                Date saleDate = resultSet.getDate("SaleDate");
                double totalSales = resultSet.getDouble("TotalSales");

                model.addRow(new Object[]{saleDate, totalSales});
            }

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
