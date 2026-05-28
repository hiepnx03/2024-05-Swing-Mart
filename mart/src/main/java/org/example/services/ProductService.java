package org.example.services;

import org.example.models.Product;
import org.example.models.ProductImage;
import org.example.models.SalesReceiptDetail;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public interface ProductService {
    DefaultTableModel getAllProducts();
    DefaultTableModel getAllProductDetails();
    boolean addProduct(Product product, ProductImage productImage, int supplierID, int userID);
    boolean giaoDichSanPham(SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int employeeID, int userID);
    boolean suaThongTinPhieuBanHang(int salesReceiptID, SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int userID);
    boolean xoaPhieuBanHang(int salesReceiptID);
    boolean updateProduct(Product product, ProductImage productImage, int supplierID, int userID);
    boolean deleteProduct(int productID);
    boolean checkFileNameExists(String fileName);
    int getStockQuantity(int productID);
    double getUnitPrice(int productID) throws SQLException;
    DefaultTableModel getAllProductGiaoDichDetails();
    boolean addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail);
    boolean updateStockQuantity(int productID, int newStockQuantity);
}
