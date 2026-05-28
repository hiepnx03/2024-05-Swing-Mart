package org.example.repositories.impl;

import org.example.connect.MyConnection;
import org.example.models.Product;
import org.example.models.ProductImage;
import org.example.models.SalesReceiptDetail;
import org.example.repositories.ProductRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM Products";
    
    private static final String SQL_GET_ALL_PRODUCT_DETAILS = 
        "SELECT p.ProductID AS 'Mã sản phẩm', p.ProductName AS 'Tên sản phẩm', p.Category AS 'Danh mục', " +
        "p.StockQuantity AS 'Số lượng tồn kho', p.UnitPrice AS 'Đơn giá', s.SupplierName AS 'Nhà cung cấp', " +
        "p.CreatedBy AS 'Tạo bởi', uc.Username AS 'Người tạo', p.UpdatedBy AS 'Cập nhật bởi', " +
        "uu.Username AS 'Người cập nhật', p.UpdatedAt AS 'Thời gian cập nhật', pi.ImageUrl AS 'Hình ảnh' " +
        "FROM Products p " +
        "LEFT JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
        "LEFT JOIN Users uc ON p.CreatedBy = uc.UserID " +
        "LEFT JOIN Users uu ON p.UpdatedBy = uu.UserID " +
        "LEFT JOIN ProductImages pi ON p.ProductID = pi.ProductID";

    private static final String SQL_INSERT_PRODUCT = 
        "INSERT INTO Products (ProductName, Category, StockQuantity, UnitPrice, SupplierID, CreatedBy, UpdatedBy, UpdatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_PRODUCT_IMAGE = 
        "INSERT INTO ProductImages (ProductID, ImageUrl, CreatedAt) VALUES (?, ?, ?)";

    private static final String SQL_INSERT_SALES_RECEIPT = 
        "INSERT INTO SalesReceipts (EmployeeID, SaleDate, TotalAmount, CustomerName, PaymentMethod, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_SALES_RECEIPT_DETAIL = 
        "INSERT INTO SalesReceiptDetails (SalesReceiptID, ProductID, Quantity, UnitPrice, TotalPrice, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_PRODUCT_STOCK = 
        "UPDATE Products SET StockQuantity = StockQuantity - ? WHERE ProductID = ?";

    private static final String SQL_UPDATE_SALES_RECEIPT = 
        "UPDATE SalesReceipts SET CustomerName = ?, PaymentMethod = ?, UpdatedBy = ?, UpdatedAt = ? WHERE SalesReceiptID = ?";
    private static final String SQL_UPDATE_SALES_RECEIPT_DETAIL = 
        "UPDATE SalesReceiptDetails SET ProductID = ?, Quantity = ?, UnitPrice = ?, TotalPrice = ?, UpdatedBy = ?, UpdatedAt = ? WHERE SalesReceiptID = ?";

    private static final String SQL_DELETE_SALES_RECEIPT_DETAIL = 
        "DELETE FROM SalesReceiptDetails WHERE SalesReceiptID = ?";
    private static final String SQL_DELETE_SALES_RECEIPT = 
        "DELETE FROM SalesReceipts WHERE SalesReceiptID = ?";

    private static final String SQL_UPDATE_PRODUCT = 
        "UPDATE Products SET ProductName = ?, Category = ?, StockQuantity = ?, UnitPrice = ?, SupplierID = ?, UpdatedBy = ?, UpdatedAt = ? WHERE ProductID = ?";
    private static final String SQL_UPDATE_PRODUCT_IMAGE = 
        "UPDATE ProductImages SET ImageUrl = ? WHERE ProductID = ?";

    private static final String SQL_DELETE_PRODUCT_IMAGE = "DELETE FROM ProductImages WHERE ProductID = ?";
    private static final String SQL_DELETE_PRICING = "DELETE FROM Pricing WHERE ProductID = ?";
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM Products WHERE ProductID = ?";
    private static final String SQL_DELETE_SALES_RECEIPT_DETAILS_BY_PRODUCT = "DELETE FROM salesreceiptdetails WHERE ProductID = ?";

    private static final String SQL_CHECK_FILE_NAME = "SELECT COUNT(*) AS count FROM ProductImages WHERE ImageUrl = ?";
    private static final String SQL_GET_STOCK_QUANTITY = "SELECT StockQuantity FROM Products WHERE ProductID = ?";
    private static final String SQL_GET_UNIT_PRICE = "SELECT UnitPrice FROM Products WHERE ProductID = ?";
    
    private static final String SQL_UPDATE_STOCK_QUANTITY_DIRECT = "UPDATE Products SET StockQuantity = ? WHERE ProductID = ?";

    @Override
    public DefaultTableModel getAllProducts() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Mã nhà cung cấp", "Tạo bởi", "Cập nhật bởi", "Thời gian cập nhật"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_PRODUCTS);
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

    @Override
    public DefaultTableModel getAllProductDetails() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Đơn giá", "Nhà cung cấp", "Tạo bởi", "Người tạo", "Cập nhật bởi", "Người cập nhật", "Thời gian cập nhật", "Hình ảnh"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_PRODUCT_DETAILS);
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

    @Override
    public boolean addProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement insertProductStmt = conn.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertProductImageStmt = conn.prepareStatement(SQL_INSERT_PRODUCT_IMAGE)) {

            conn.setAutoCommit(false);

            insertProductStmt.setString(1, product.getProductName());
            insertProductStmt.setString(2, product.getCategory());
            insertProductStmt.setInt(3, product.getStockQuantity());
            insertProductStmt.setDouble(4, product.getUnitPrice());
            insertProductStmt.setInt(5, supplierID);
            insertProductStmt.setInt(6, userID);
            insertProductStmt.setInt(7, userID);
            insertProductStmt.setTimestamp(8, new Timestamp(new Date().getTime()));
            insertProductStmt.executeUpdate();

            ResultSet generatedKeys = insertProductStmt.getGeneratedKeys();
            int productID = 0;
            if (generatedKeys.next()) {
                productID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating product failed, no ID obtained.");
            }

            insertProductImageStmt.setInt(1, productID);
            insertProductImageStmt.setString(2, productImage.getImageUrl());
            insertProductImageStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertProductImageStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean giaoDichSanPham(SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int employeeID, int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement insertSalesReceiptStmt = conn.prepareStatement(SQL_INSERT_SALES_RECEIPT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertSalesReceiptDetailStmt = conn.prepareStatement(SQL_INSERT_SALES_RECEIPT_DETAIL);
             PreparedStatement updateStmt = conn.prepareStatement(SQL_UPDATE_PRODUCT_STOCK)) {

            conn.setAutoCommit(false);

            insertSalesReceiptStmt.setInt(1, employeeID);
            insertSalesReceiptStmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            insertSalesReceiptStmt.setBigDecimal(3, BigDecimal.valueOf(salesReceiptDetail.getTotalPrice()));
            insertSalesReceiptStmt.setString(4, customerName);
            insertSalesReceiptStmt.setString(5, paymentMethod);
            insertSalesReceiptStmt.setInt(6, userID);
            insertSalesReceiptStmt.setInt(7, userID);
            insertSalesReceiptStmt.executeUpdate();

            ResultSet generatedKeys = insertSalesReceiptStmt.getGeneratedKeys();
            int salesReceiptID = -1;
            if (generatedKeys.next()) {
                salesReceiptID = generatedKeys.getInt(1);
            }

            insertSalesReceiptDetailStmt.setInt(1, salesReceiptID);
            insertSalesReceiptDetailStmt.setInt(2, salesReceiptDetail.getProductID());
            insertSalesReceiptDetailStmt.setInt(3, salesReceiptDetail.getQuantity());
            insertSalesReceiptDetailStmt.setBigDecimal(4, BigDecimal.valueOf(salesReceiptDetail.getUnitPrice()));
            insertSalesReceiptDetailStmt.setBigDecimal(5, BigDecimal.valueOf(salesReceiptDetail.getTotalPrice()));
            insertSalesReceiptDetailStmt.setInt(6, userID);
            insertSalesReceiptDetailStmt.setInt(7, userID);
            insertSalesReceiptDetailStmt.executeUpdate();

            updateStmt.setInt(1, salesReceiptDetail.getQuantity());
            updateStmt.setInt(2, salesReceiptDetail.getProductID());
            updateStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean suaThongTinPhieuBanHang(int salesReceiptID, SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement updateSalesReceiptStmt = conn.prepareStatement(SQL_UPDATE_SALES_RECEIPT);
             PreparedStatement updateSalesReceiptDetailStmt = conn.prepareStatement(SQL_UPDATE_SALES_RECEIPT_DETAIL)) {

            conn.setAutoCommit(false);

            updateSalesReceiptStmt.setString(1, customerName);
            updateSalesReceiptStmt.setString(2, paymentMethod);
            updateSalesReceiptStmt.setInt(3, userID);
            updateSalesReceiptStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            updateSalesReceiptStmt.setInt(5, salesReceiptID);
            updateSalesReceiptStmt.executeUpdate();

            updateSalesReceiptDetailStmt.setInt(1, salesReceiptDetail.getProductID());
            updateSalesReceiptDetailStmt.setInt(2, salesReceiptDetail.getQuantity());
            updateSalesReceiptDetailStmt.setBigDecimal(3, BigDecimal.valueOf(salesReceiptDetail.getUnitPrice()));
            updateSalesReceiptDetailStmt.setBigDecimal(4, BigDecimal.valueOf(salesReceiptDetail.getTotalPrice()));
            updateSalesReceiptDetailStmt.setInt(5, userID);
            updateSalesReceiptDetailStmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            updateSalesReceiptDetailStmt.setInt(7, salesReceiptID);
            updateSalesReceiptDetailStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaPhieuBanHang(int salesReceiptID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement deleteSalesReceiptDetailStmt = conn.prepareStatement(SQL_DELETE_SALES_RECEIPT_DETAIL);
             PreparedStatement deleteSalesReceiptStmt = conn.prepareStatement(SQL_DELETE_SALES_RECEIPT)) {

            conn.setAutoCommit(false);

            deleteSalesReceiptDetailStmt.setInt(1, salesReceiptID);
            deleteSalesReceiptDetailStmt.executeUpdate();

            deleteSalesReceiptStmt.setInt(1, salesReceiptID);
            deleteSalesReceiptStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement updateProductStmt = conn.prepareStatement(SQL_UPDATE_PRODUCT);
             PreparedStatement updateProductImageStmt = conn.prepareStatement(SQL_UPDATE_PRODUCT_IMAGE)) {

            conn.setAutoCommit(false);

            updateProductStmt.setString(1, product.getProductName());
            updateProductStmt.setString(2, product.getCategory());
            updateProductStmt.setInt(3, product.getStockQuantity());
            updateProductStmt.setDouble(4, product.getUnitPrice());
            updateProductStmt.setInt(5, supplierID);
            updateProductStmt.setInt(6, userID);
            updateProductStmt.setTimestamp(7, new Timestamp(new Date().getTime()));
            updateProductStmt.setInt(8, product.getProductID());
            updateProductStmt.executeUpdate();

            updateProductImageStmt.setString(1, productImage.getImageUrl());
            updateProductImageStmt.setInt(2, product.getProductID());
            updateProductImageStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int productID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement deleteProductImageStmt = conn.prepareStatement(SQL_DELETE_PRODUCT_IMAGE);
             PreparedStatement deletePricingStmt = conn.prepareStatement(SQL_DELETE_PRICING);
             PreparedStatement deleteSalesReceiptDetailsStmt = conn.prepareStatement(SQL_DELETE_SALES_RECEIPT_DETAILS_BY_PRODUCT);
             PreparedStatement deleteProductStmt = conn.prepareStatement(SQL_DELETE_PRODUCT)) {

            conn.setAutoCommit(false);

            deleteProductImageStmt.setInt(1, productID);
            deleteProductImageStmt.executeUpdate();

            deletePricingStmt.setInt(1, productID);
            deletePricingStmt.executeUpdate();

            deleteSalesReceiptDetailsStmt.setInt(1, productID);
            deleteSalesReceiptDetailsStmt.executeUpdate();

            deleteProductStmt.setInt(1, productID);
            deleteProductStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) e;
                if (sqlEx.getErrorCode() == 1451 || sqlEx.getErrorCode() == 1452) {
                    JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm vì có dữ liệu liên quan đến sản phẩm này.");
                }
            }
            return false;
        }
    }

    @Override
    public boolean checkFileNameExists(String fileName) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL_CHECK_FILE_NAME)) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getStockQuantity(int productID) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_GET_STOCK_QUANTITY)) {
            stmt.setInt(1, productID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("StockQuantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public double getUnitPrice(int productID) throws SQLException {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_GET_UNIT_PRICE)) {
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

    @Override
    public DefaultTableModel getAllProductGiaoDichDetails() {
        return getAllProductDetails(); // Reuses the exact same JOIN query and mapping logic
    }

    @Override
    public boolean addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
        String query = "INSERT INTO SalesReceiptDetails (SalesReceiptID, ProductID, Quantity, UnitPrice, TotalPrice, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement insertSalesReceiptDetailStmt = conn.prepareStatement(query)) {
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

    @Override
    public boolean updateStockQuantity(int productID, int newStockQuantity) {
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement updateStockQuantityStmt = conn.prepareStatement(SQL_UPDATE_STOCK_QUANTITY_DIRECT)) {
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
