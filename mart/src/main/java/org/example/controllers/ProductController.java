package org.example.controllers;

import org.example.connect.MyConnection;
import org.example.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductController extends Component {

    private Connection connection;

    public ProductController() {
        // Initialize database connection
        this.connection = MyConnection.getConnection();
    }

    public DefaultTableModel getAllProducts() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Mã nhà cung cấp", "Tạo bởi", "Cập nhật bởi", "Thời gian cập nhật"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT * FROM Products";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String category = resultSet.getString("Category");
                int stockQuantity = resultSet.getInt("StockQuantity");
                double unitPrice = resultSet.getDouble("UnitPrice");
                int supplierID = resultSet.getInt("SupplierID");
                int createdBy = resultSet.getInt("CreatedBy");
                int updatedBy = resultSet.getInt("UpdatedBy");
                Date updatedAt = resultSet.getTimestamp("UpdatedAt");

                model.addRow(new Object[]{productID, productName, category, stockQuantity, unitPrice, supplierID, createdBy, updatedBy, updatedAt});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }


    public DefaultTableModel getAllProductDetails() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Nhà cung cấp", "Tạo bởi", "Người tạo", "Cập nhật bởi", "Người cập nhật", "Thời gian cập nhật", "Hình ảnh"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT " +
                "p.ProductID AS 'Mã sản phẩm', " +
                "p.ProductName AS 'Tên sản phẩm', " +
                "p.Category AS 'Danh mục', " +
                "p.StockQuantity AS 'Số lượng tồn kho', " +
                "p.UnitPrice AS 'Đơn giá', " +
                "s.SupplierName AS 'Nhà cung cấp', " +
                "p.CreatedBy AS 'Tạo bởi', " +
                "uc.Username AS 'Người tạo', " +
                "p.UpdatedBy AS 'Cập nhật bởi', " +
                "uu.Username AS 'Người cập nhật', " +
                "p.UpdatedAt AS 'Thời gian cập nhật', " +
                "pi.ImageUrl AS 'Hình ảnh' " +
                "FROM Products p " +
                "LEFT JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
                "LEFT JOIN Users uc ON p.CreatedBy = uc.UserID " +
                "LEFT JOIN Users uu ON p.UpdatedBy = uu.UserID " +
                "LEFT JOIN ProductImages pi ON p.ProductID = pi.ProductID";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productID = resultSet.getInt("Mã sản phẩm");
                String productName = resultSet.getString("Tên sản phẩm");
                String category = resultSet.getString("Danh mục");
                int stockQuantity = resultSet.getInt("Số lượng tồn kho");
                double unitPrice = resultSet.getDouble("Đơn giá");
                String supplierName = resultSet.getString("Nhà cung cấp");
                int createdBy = resultSet.getInt("Tạo bởi");
                String creatorUsername = resultSet.getString("Người tạo");
                int updatedBy = resultSet.getInt("Cập nhật bởi");
                String updaterUsername = resultSet.getString("Người cập nhật");
                java.util.Date updatedAt = resultSet.getTimestamp("Thời gian cập nhật");
                String imageUrl = resultSet.getString("Hình ảnh");

                model.addRow(new Object[]{productID, productName, category, stockQuantity, unitPrice, supplierName, createdBy, creatorUsername, updatedBy, updaterUsername, updatedAt, imageUrl});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }


    public boolean addProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        String insertProductQuery = "INSERT INTO Products (ProductName, Category, StockQuantity, UnitPrice, SupplierID, CreatedBy, UpdatedBy, UpdatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String insertProductImageQuery = "INSERT INTO ProductImages (ProductID, ImageUrl, CreatedAt) VALUES (?, ?, ?)";

        try {
            // Thêm sản phẩm
            PreparedStatement insertProductStmt = connection.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS);
            insertProductStmt.setString(1, product.getProductName());
            insertProductStmt.setString(2, product.getCategory());
            insertProductStmt.setInt(3, product.getStockQuantity());
            insertProductStmt.setDouble(4, product.getUnitPrice());
            insertProductStmt.setInt(5, supplierID);
            insertProductStmt.setInt(6, userID); // Sử dụng userID cho trường CreatedBy
            insertProductStmt.setInt(7, userID); // Sử dụng userID cho trường UpdatedBy
            insertProductStmt.setTimestamp(8, new Timestamp(new Date().getTime()));
            insertProductStmt.executeUpdate();

            // Lấy ID của sản phẩm vừa thêm
            ResultSet generatedKeys = insertProductStmt.getGeneratedKeys();
            int productID = 0;
            if (generatedKeys.next()) {
                productID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating product failed, no ID obtained.");
            }

            // Thêm hình ảnh sản phẩm
            PreparedStatement insertProductImageStmt = connection.prepareStatement(insertProductImageQuery);
            insertProductImageStmt.setInt(1, productID);
            insertProductImageStmt.setString(2, productImage.getImageUrl());
            insertProductImageStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertProductImageStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean giaoDichSanPham(SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int employeeID, int userID) {
        String insertSalesReceiptSQL = "INSERT INTO SalesReceipts (EmployeeID, SaleDate, TotalAmount, CustomerName, PaymentMethod, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertSalesReceiptDetailSQL = "INSERT INTO SalesReceiptDetails (SalesReceiptID, ProductID, Quantity, UnitPrice, TotalPrice, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateProductStockSQL = "UPDATE Products SET StockQuantity = StockQuantity - ? WHERE ProductID = ?";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement insertSalesReceiptStmt = conn.prepareStatement(insertSalesReceiptSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertSalesReceiptDetailStmt = conn.prepareStatement(insertSalesReceiptDetailSQL);
             PreparedStatement updateStmt = conn.prepareStatement(updateProductStockSQL)) {

            // Thực hiện giao dịch trong một transaction
            conn.setAutoCommit(false);

            // Thêm thông tin phiếu bán hàng vào bảng SalesReceipts
            insertSalesReceiptStmt.setInt(1, employeeID);
            // Thay đổi SaleDate thành ngày hiện tại hoặc ngày của giao dịch
            insertSalesReceiptStmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            // Thay đổi TotalAmount thành tổng giá tiền của chi tiết phiếu bán hàng
            insertSalesReceiptStmt.setBigDecimal(3, BigDecimal.valueOf(salesReceiptDetail.getTotalPrice()));
            insertSalesReceiptStmt.setString(4, customerName); // Sử dụng tên khách hàng từ tham số
            insertSalesReceiptStmt.setString(5, paymentMethod); // Sử dụng phương thức thanh toán từ tham số
            insertSalesReceiptStmt.setInt(6, userID);
            insertSalesReceiptStmt.setInt(7, userID);
            insertSalesReceiptStmt.executeUpdate();

            // Lấy SalesReceiptID vừa được tạo
            ResultSet generatedKeys = insertSalesReceiptStmt.getGeneratedKeys();
            int salesReceiptID = -1;
            if (generatedKeys.next()) {
                salesReceiptID = generatedKeys.getInt(1);
            }

            // Thêm thông tin chi tiết phiếu bán hàng vào bảng SalesReceiptDetails
            insertSalesReceiptDetailStmt.setInt(1, salesReceiptID);
            insertSalesReceiptDetailStmt.setInt(2, salesReceiptDetail.getProductID());
            insertSalesReceiptDetailStmt.setInt(3, salesReceiptDetail.getQuantity());
            insertSalesReceiptDetailStmt.setBigDecimal(4, BigDecimal.valueOf(salesReceiptDetail.getUnitPrice()));
            insertSalesReceiptDetailStmt.setBigDecimal(5, BigDecimal.valueOf(salesReceiptDetail.getTotalPrice()));
            insertSalesReceiptDetailStmt.setInt(6, userID);
            insertSalesReceiptDetailStmt.setInt(7, userID);
            insertSalesReceiptDetailStmt.executeUpdate();

            // Cập nhật số lượng sản phẩm trong bảng Products
            updateStmt.setInt(1, salesReceiptDetail.getQuantity());
            updateStmt.setInt(2, salesReceiptDetail.getProductID());
            updateStmt.executeUpdate();

            // Commit transaction
            conn.commit();

            return true; // Giao dịch thành công

        } catch (SQLException e) {
            // Rollback transaction nếu có lỗi
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false; // Giao dịch không thành công
        }
    }


    private int getInsertedSupplierID() throws SQLException {
        // Lấy ID của nhà cung cấp vừa được thêm vào
        String selectLastSupplierIDQuery = "SELECT LAST_INSERT_ID()";
        PreparedStatement selectLastSupplierIDStmt = connection.prepareStatement(selectLastSupplierIDQuery);
        ResultSet resultSet = selectLastSupplierIDStmt.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private int getInsertedProductID() throws SQLException {
        // Lấy ID của sản phẩm vừa được thêm vào
        String selectLastProductIDQuery = "SELECT LAST_INSERT_ID()";
        PreparedStatement selectLastProductIDStmt = connection.prepareStatement(selectLastProductIDQuery);
        ResultSet resultSet = selectLastProductIDStmt.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public boolean updateProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        String updateProductQuery = "UPDATE Products SET ProductName = ?, Category = ?, StockQuantity = ?, UnitPrice = ?, SupplierID = ?, UpdatedBy = ?, UpdatedAt = ? WHERE ProductID = ?";
        String updateProductImageQuery = "UPDATE ProductImages SET ImageUrl = ? WHERE ProductID = ?";

        try {
            // Bắt đầu giao dịch
            connection.setAutoCommit(false);

            // Cập nhật thông tin sản phẩm
            PreparedStatement updateProductStmt = connection.prepareStatement(updateProductQuery);
            updateProductStmt.setString(1, product.getProductName());
            updateProductStmt.setString(2, product.getCategory());
            updateProductStmt.setInt(3, product.getStockQuantity());
            updateProductStmt.setDouble(4, product.getUnitPrice());
            updateProductStmt.setInt(5, supplierID);
            updateProductStmt.setInt(6, userID);
            updateProductStmt.setTimestamp(7, new Timestamp(new Date().getTime()));
            updateProductStmt.setInt(8, product.getProductID());
            updateProductStmt.executeUpdate();

            // Cập nhật thông tin hình ảnh sản phẩm
            PreparedStatement updateProductImageStmt = connection.prepareStatement(updateProductImageQuery);
            updateProductImageStmt.setString(1, productImage.getImageUrl());
            updateProductImageStmt.setInt(2, product.getProductID());
            updateProductImageStmt.executeUpdate();

            // Hoàn thành giao dịch
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                // Rollback nếu có lỗi
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Đặt lại trạng thái tự động commit
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public boolean deleteProduct(int productID) {
        String deleteProductImageQuery = "DELETE FROM ProductImages WHERE ProductID = ?";
        String deletePricingQuery = "DELETE FROM Pricing WHERE ProductID = ?";
        String deleteProductQuery = "DELETE FROM Products WHERE ProductID = ?";
        String deleteSalesReceiptDetailsQuery = "DELETE FROM salesreceiptdetails WHERE ProductID = ?";

        try {
            // Begin transaction
            connection.setAutoCommit(false);

            // Delete product images
            PreparedStatement deleteProductImageStmt = connection.prepareStatement(deleteProductImageQuery);
            deleteProductImageStmt.setInt(1, productID);
            deleteProductImageStmt.executeUpdate();

            // Delete related pricing rows
            PreparedStatement deletePricingStmt = connection.prepareStatement(deletePricingQuery);
            deletePricingStmt.setInt(1, productID);
            deletePricingStmt.executeUpdate();

            // Delete related sales receipt details
            PreparedStatement deleteSalesReceiptDetailsStmt = connection.prepareStatement(deleteSalesReceiptDetailsQuery);
            deleteSalesReceiptDetailsStmt.setInt(1, productID);
            deleteSalesReceiptDetailsStmt.executeUpdate();

            // Delete product
            PreparedStatement deleteProductStmt = connection.prepareStatement(deleteProductQuery);
            deleteProductStmt.setInt(1, productID);
            deleteProductStmt.executeUpdate();

            // Commit transaction
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                // Rollback if there's an error
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            // Handle the specific SQLIntegrityConstraintViolationException
            if (e instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) e;
                if (sqlEx.getErrorCode() == 1451 || sqlEx.getErrorCode() == 1452) {
                    // Display a user-friendly error message for foreign key constraint violation
                    JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm vì có dữ liệu liên quan đến sản phẩm này.");
                    return false;
                }
            }

            e.printStackTrace();
            return false;
        } finally {
            try {
                // Reset auto-commit mode
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public boolean checkFileNameExists(String fileName) {
        try {
            // Prepare the SQL statement to check if the file name exists
            String query = "SELECT COUNT(*) AS count FROM ProductImages WHERE ImageUrl = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the file name parameter in the prepared statement
                statement.setString(1, fileName);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if any row exists with the given file name
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int getStockQuantity(int productID) {
        String query = "SELECT StockQuantity FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("StockQuantity");
                } else {
                    throw new SQLException("Product not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

    public double getUnitPrice(int productID) throws SQLException {
        String query = "SELECT UnitPrice FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("UnitPrice");
                } else {
                    throw new SQLException("Product not found.");
                }
            }
        }
    }

    public DefaultTableModel getAllProductGiaoDichDetails() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Nhà cung cấp", "Tạo bởi", "Người tạo", "Cập nhật bởi", "Người cập nhật", "Thời gian cập nhật", "Hình ảnh"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String sql = "SELECT " +
                "p.ProductID AS 'Mã sản phẩm', " +
                "p.ProductName AS 'Tên sản phẩm', " +
                "p.Category AS 'Danh mục', " +
                "p.StockQuantity AS 'Số lượng tồn kho', " +
                "p.UnitPrice AS 'Đơn giá', " +
                "s.SupplierName AS 'Nhà cung cấp', " +
                "p.CreatedBy AS 'Tạo bởi', " +
                "uc.Username AS 'Người tạo', " +
                "p.UpdatedBy AS 'Cập nhật bởi', " +
                "uu.Username AS 'Người cập nhật', " +
                "p.UpdatedAt AS 'Thời gian cập nhật', " +
                "pi.ImageUrl AS 'Hình ảnh' " +
                "FROM Products p " +
                "LEFT JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
                "LEFT JOIN Users uc ON p.CreatedBy = uc.UserID " +
                "LEFT JOIN Users uu ON p.UpdatedBy = uu.UserID " +
                "LEFT JOIN ProductImages pi ON p.ProductID = pi.ProductID";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productID = resultSet.getInt("Mã sản phẩm");
                String productName = resultSet.getString("Tên sản phẩm");
                String category = resultSet.getString("Danh mục");
                int stockQuantity = resultSet.getInt("Số lượng tồn kho");
                double unitPrice = resultSet.getDouble("Đơn giá");
                String supplierName = resultSet.getString("Nhà cung cấp");
                int createdBy = resultSet.getInt("Tạo bởi");
                String creatorUsername = resultSet.getString("Người tạo");
                int updatedBy = resultSet.getInt("Cập nhật bởi");
                String updaterUsername = resultSet.getString("Người cập nhật");
                java.util.Date updatedAt = resultSet.getTimestamp("Thời gian cập nhật");
                String imageUrl = resultSet.getString("Hình ảnh");

                model.addRow(new Object[]{productID, productName, category, stockQuantity, unitPrice, supplierName, createdBy, creatorUsername, updatedBy, updaterUsername, updatedAt, imageUrl});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public boolean addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
        String insertSalesReceiptDetailQuery = "INSERT INTO SalesReceiptDetails (SalesReceiptID, ProductID, Quantity, UnitPrice, TotalPrice, CreatedBy, UpdatedBy) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Thực hiện thêm chi tiết giao dịch vào cơ sở dữ liệu
            PreparedStatement insertSalesReceiptDetailStmt = connection.prepareStatement(insertSalesReceiptDetailQuery);
            insertSalesReceiptDetailStmt.setInt(1, salesReceiptDetail.getSalesReceiptID());
            insertSalesReceiptDetailStmt.setInt(2, salesReceiptDetail.getProductID());
            insertSalesReceiptDetailStmt.setInt(3, salesReceiptDetail.getQuantity());
            insertSalesReceiptDetailStmt.setDouble(4, salesReceiptDetail.getUnitPrice());
            insertSalesReceiptDetailStmt.setDouble(5, salesReceiptDetail.getTotalPrice());
            insertSalesReceiptDetailStmt.setInt(6, salesReceiptDetail.getCreatedBy());
            insertSalesReceiptDetailStmt.setInt(7, salesReceiptDetail.getUpdatedBy());
            insertSalesReceiptDetailStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStockQuantity(int productID, int newStockQuantity) {
        String updateStockQuantityQuery = "UPDATE Products SET StockQuantity = ? WHERE ProductID = ?";

        try {
            // Thực hiện cập nhật số lượng hàng trong cơ sở dữ liệu
            PreparedStatement updateStockQuantityStmt = connection.prepareStatement(updateStockQuantityQuery);
            updateStockQuantityStmt.setInt(1, newStockQuantity);
            updateStockQuantityStmt.setInt(2, productID);
            updateStockQuantityStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
