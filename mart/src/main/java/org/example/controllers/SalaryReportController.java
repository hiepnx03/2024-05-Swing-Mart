package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.SalaryReport;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SalaryReportController {
    private Connection connection;


    public SalaryReportController() {
        this.connection = MyConnection.getConnection();
    }

    public int getTotalQuantitySoldByDate(Date startDate, Date endDate) {
        String query = "SELECT SUM(Quantity) AS TotalQuantity FROM SalesReceiptDetails INNER JOIN SalesReceipts ON SalesReceiptDetails.SalesReceiptID = SalesReceipts.SalesReceiptID WHERE SaleDate BETWEEN ? AND ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TotalQuantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public double getTotalRevenueByDate(Date startDate, Date endDate) {
        String query = "SELECT SUM(TotalAmount) AS TotalRevenue FROM SalesReceipts WHERE SaleDate BETWEEN ? AND ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TotalRevenue");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    public Map<String, Double> getRevenueByProduct() {
        Map<String, Double> revenueMap = new HashMap<>();
        String query = "SELECT ProductID, SUM(TotalPrice) AS TotalRevenue FROM SalesReceiptDetails GROUP BY ProductID";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                double totalRevenue = rs.getDouble("TotalRevenue");
                revenueMap.put("Product ID: " + productID, totalRevenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueMap;
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
        String query = "SELECT sr.SalesReceiptID, sr.SaleDate, sr.TotalAmount, sr.CustomerName, sr.PaymentMethod, e.FirstName, e.LastName, srd.ProductID, p.ProductName, SUM(srd.Quantity) AS SoldQuantity " +
                "FROM SalesReceipts sr " +
                "INNER JOIN Employees e ON sr.EmployeeID = e.EmployeeID " +
                "INNER JOIN SalesReceiptDetails srd ON sr.SalesReceiptID = srd.SalesReceiptID " +
                "INNER JOIN Products p ON srd.ProductID = p.ProductID " +
                "GROUP BY sr.SalesReceiptID, sr.SaleDate, sr.TotalAmount, sr.CustomerName, sr.PaymentMethod, e.FirstName, e.LastName, srd.ProductID, p.ProductName";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"SalesReceiptID", "SaleDate", "TotalAmount", "CustomerName", "PaymentMethod", "FirstName", "LastName", "ProductID", "ProductName", "SoldQuantity"}, 0);

            while (resultSet.next()) {
                int salesReceiptID = resultSet.getInt("SalesReceiptID");
                Date saleDate = resultSet.getDate("SaleDate");
                double totalAmount = resultSet.getDouble("TotalAmount");
                String customerName = resultSet.getString("CustomerName");
                String paymentMethod = resultSet.getString("PaymentMethod");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                int soldQuantity = resultSet.getInt("SoldQuantity");

                model.addRow(new Object[]{salesReceiptID, saleDate, totalAmount, customerName, paymentMethod, firstName, lastName, productID, productName, soldQuantity});
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

    public boolean deleteSalesReport(int salesReceiptID) {
        String deleteDetailsQuery = "DELETE FROM SalesReceiptDetails WHERE SalesReceiptID = ?";
        String deleteReportQuery = "DELETE FROM SalesReceipts WHERE SalesReceiptID = ?";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement deleteDetailsStmt = conn.prepareStatement(deleteDetailsQuery);
             PreparedStatement deleteReportStmt = conn.prepareStatement(deleteReportQuery)) {

            // Xóa tất cả các chi tiết báo cáo doanh thu liên quan
            deleteDetailsStmt.setInt(1, salesReceiptID);
            deleteDetailsStmt.executeUpdate();

            // Sau đó xóa báo cáo doanh thu
            deleteReportStmt.setInt(1, salesReceiptID);
            int rowsAffected = deleteReportStmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
