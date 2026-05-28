package org.example.controllers;

import org.example.models.Product;
import org.example.models.ProductImage;
import org.example.models.SalesReceiptDetail;
import org.example.services.ProductService;
import org.example.services.impl.ProductServiceImpl;

import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.sql.SQLException;

public class ProductController extends Component {

    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
    }

    public DefaultTableModel getAllProducts() {
        return productService.getAllProducts();
    }

    public DefaultTableModel getAllProductDetails() {
        return productService.getAllProductDetails();
    }

    public boolean addProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        return productService.addProduct(product, productImage, supplierID, userID);
    }

    public boolean giaoDichSanPham(SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int employeeID, int userID) {
        return productService.giaoDichSanPham(salesReceiptDetail, customerName, paymentMethod, employeeID, userID);
    }

    public boolean suaThongTinPhieuBanHang(int salesReceiptID, SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int userID) {
        return productService.suaThongTinPhieuBanHang(salesReceiptID, salesReceiptDetail, customerName, paymentMethod, userID);
    }

    public boolean xoaPhieuBanHang(int salesReceiptID) {
        return productService.xoaPhieuBanHang(salesReceiptID);
    }

    public boolean updateProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        return productService.updateProduct(product, productImage, supplierID, userID);
    }

    public boolean deleteProduct(int productID) {
        return productService.deleteProduct(productID);
    }

    public boolean checkFileNameExists(String fileName) {
        return productService.checkFileNameExists(fileName);
    }

    public int getStockQuantity(int productID) {
        return productService.getStockQuantity(productID);
    }

    public double getUnitPrice(int productID) throws SQLException {
        return productService.getUnitPrice(productID);
    }

    public DefaultTableModel getAllProductGiaoDichDetails() {
        return productService.getAllProductGiaoDichDetails();
    }

    public boolean addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
        return productService.addSalesReceiptDetail(salesReceiptDetail);
    }

    public boolean updateStockQuantity(int productID, int newStockQuantity) {
        return productService.updateStockQuantity(productID, newStockQuantity);
    }
}
