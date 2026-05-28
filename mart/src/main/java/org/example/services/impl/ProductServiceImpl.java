package org.example.services.impl;

import org.example.models.Product;
import org.example.models.ProductImage;
import org.example.models.SalesReceiptDetail;
import org.example.repositories.ProductRepository;
import org.example.repositories.impl.ProductRepositoryImpl;
import org.example.services.ProductService;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public DefaultTableModel getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public DefaultTableModel getAllProductDetails() {
        return productRepository.getAllProductDetails();
    }

    @Override
    public boolean addProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        return productRepository.addProduct(product, productImage, supplierID, userID);
    }

    @Override
    public boolean giaoDichSanPham(SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int employeeID, int userID) {
        return productRepository.giaoDichSanPham(salesReceiptDetail, customerName, paymentMethod, employeeID, userID);
    }

    @Override
    public boolean suaThongTinPhieuBanHang(int salesReceiptID, SalesReceiptDetail salesReceiptDetail, String customerName, String paymentMethod, int userID) {
        return productRepository.suaThongTinPhieuBanHang(salesReceiptID, salesReceiptDetail, customerName, paymentMethod, userID);
    }

    @Override
    public boolean xoaPhieuBanHang(int salesReceiptID) {
        return productRepository.xoaPhieuBanHang(salesReceiptID);
    }

    @Override
    public boolean updateProduct(Product product, ProductImage productImage, int supplierID, int userID) {
        return productRepository.updateProduct(product, productImage, supplierID, userID);
    }

    @Override
    public boolean deleteProduct(int productID) {
        return productRepository.deleteProduct(productID);
    }

    @Override
    public boolean checkFileNameExists(String fileName) {
        return productRepository.checkFileNameExists(fileName);
    }

    @Override
    public int getStockQuantity(int productID) {
        return productRepository.getStockQuantity(productID);
    }

    @Override
    public double getUnitPrice(int productID) throws SQLException {
        return productRepository.getUnitPrice(productID);
    }

    @Override
    public DefaultTableModel getAllProductGiaoDichDetails() {
        return productRepository.getAllProductGiaoDichDetails();
    }

    @Override
    public boolean addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
        return productRepository.addSalesReceiptDetail(salesReceiptDetail);
    }

    @Override
    public boolean updateStockQuantity(int productID, int newStockQuantity) {
        return productRepository.updateStockQuantity(productID, newStockQuantity);
    }
}
