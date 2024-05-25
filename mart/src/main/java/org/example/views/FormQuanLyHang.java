/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.views;

import org.example.controllers.*;
import org.example.models.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 *
 * @author hiepn
 */
class ImageRenderer extends DefaultTableCellRenderer {
    JLabel label = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            // Assuming value is the name of the image file
            String imagePath = "images/" + value.toString();
            URL imageURL = getClass().getClassLoader().getResource(imagePath);
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                if (icon.getIconWidth() > 0) {
                    // Scale the image to fit within the cell
                    Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(image));
                } else {
                    label.setText("Image not found");
                    label.setIcon(null);
                }
            } else {
                label.setText("Image not found");
                label.setIcon(null);
            }
        } else {
            label.setText("No image");
            label.setIcon(null);
        }
        return label;
    }
}

public class FormQuanLyHang extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private InventoryReceiptController inventoryReceiptController;
    private SalesReceiptController salesReceiptController;
    private ProductController productController;
    private SupplierController supplierController;
    /**
     * Creates new form FormQuanLyHang
     */
    public FormQuanLyHang() {
        initComponents();
        // Initialize the inventoryReceiptController once in the constructor
        inventoryReceiptController = new InventoryReceiptController();
        // Call the method to show inventory receipts
        showPhieuBanHang();
        showPhieuNhapHang();
        showDanhMucSanPham();
        showSanPham();
        showNhaCungCap();
        loadSupplier();
    }


    private void loadSupplier() {
        SupplierController supplierController = new SupplierController(); // Create an instance of SupplierController
        DefaultTableModel model = supplierController.getAllSuppliers(); // Call getAllSuppliers method

        for (int i = 0; i < model.getRowCount(); i++) {
            String supplierName = (String) model.getValueAt(i, 1); // Assuming SupplierName is at column index 1
            ComboBoxNhaCungCap.addItem(supplierName); // Add supplier names to your combo box
        }

        // Select a specific supplier, for example, the first supplier in the list
        if (model.getRowCount() > 0) {
            selectSupplier((String) model.getValueAt(0, 1)); // Assuming SupplierName is at column index 1
        }
    }


    private void selectSupplier(String supplierName) {
        for (int i = 0; i < ComboBoxNhaCungCap.getItemCount(); i++) {
            String item = (String) ComboBoxNhaCungCap.getItemAt(i);
            if (item.equals(supplierName)) {
                ComboBoxNhaCungCap.setSelectedItem(item);
                break;
            }
        }
    }



    private void showNhaCungCap(){
        supplierController = new SupplierController();
        tableModel = supplierController.getAllSuppliers();
        TableNhaCungCap.setModel(tableModel);

    }

    private void showSanPham(){
        productController = new ProductController();
        tableModel = productController.getAllProductDetails();
        TableSanPham.setModel(tableModel);
        TableSanPham.getColumnModel().getColumn(11).setCellRenderer(new ImageRenderer());

    }

    private void showDanhMucSanPham(){
        productController = new ProductController();
        tableModel = productController.getAllProducts();
        TableDanhMucSanPham.setModel(tableModel);
    }


    private void showPhieuBanHang() {
        salesReceiptController = new SalesReceiptController();
        tableModel = salesReceiptController.getAllPhieuBanHang();
        TablePhieuBanHang.setModel(tableModel);
    }

    private void showPhieuNhapHang() {
        inventoryReceiptController = new InventoryReceiptController();
        tableModel = inventoryReceiptController.getAllInventoryReceipts();
        TablePhieuNhapHang.setModel(tableModel);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePhieuBanHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablePhieuNhapHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableDanhMucSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableSanPham = new javax.swing.JTable();
        btnThemSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnLamTrong = new javax.swing.JButton();
        btnChonAnhSanPham = new javax.swing.JButton();
        LabelAnhSanPham = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tbMaSanPham = new javax.swing.JTextField();
        tbTenSanPham = new javax.swing.JTextField();
        tbDanhMuc = new javax.swing.JTextField();
        tbDonGia = new javax.swing.JTextField();
        tbSoLuong = new javax.swing.JTextField();
        ComboBoxNhaCungCap = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableNhaCungCap = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbSupplierID = new javax.swing.JTextField();
        tbSupplierName = new javax.swing.JTextField();
        tbContactInfo = new javax.swing.JTextField();
        tbEmail = new javax.swing.JTextField();
        tbPhone = new javax.swing.JTextField();
        tbAddress = new javax.swing.JTextField();
        btnThemNhaCungCap = new javax.swing.JButton();
        btnSuaNhaCungCap = new javax.swing.JButton();
        btnXoaNhaCungCap = new javax.swing.JButton();
        btnLamRongNhaCungCap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Quản Lý Hàng");

        TablePhieuBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablePhieuBanHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu bán hàng", jPanel2);

        TablePhieuNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablePhieuNhapHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu nhập hàng", jPanel3);

        TableDanhMucSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TableDanhMucSanPham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý danh mục sản phẩm", jPanel4);

        TableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TableSanPham);

        btnThemSanPham.setText("Thêm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setText("Sửa");
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setText("Xóa");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnLamTrong.setText("Làm Trỗng");
        btnLamTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamTrongActionPerformed(evt);
            }
        });

        btnChonAnhSanPham.setText("Chọn ảnh");
        btnChonAnhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhSanPhamActionPerformed(evt);
            }
        });

        LabelAnhSanPham.setText("Ảnh Sản Phẩm");

        jLabel8.setText("Mã sản phẩm");

        jLabel9.setText("Tên sản phẩm");

        jLabel10.setText("Danh mục");

        jLabel11.setText("Số lượng");

        jLabel12.setText("Đơn giá");

        jLabel13.setText("Nhà cung cấp");

        tbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenSanPhamActionPerformed(evt);
            }
        });

        tbDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbDonGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(tbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(tbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(tbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tbDonGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboBoxNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LabelAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThemSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamTrong)
                        .addGap(18, 18, 18)
                        .addComponent(btnChonAnhSanPham)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(tbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(tbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(tbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham)
                    .addComponent(btnSuaSanPham)
                    .addComponent(btnXoaSanPham)
                    .addComponent(btnLamTrong)
                    .addComponent(btnChonAnhSanPham))
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Quản lý sản phẩm", jPanel5);

        TableNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableNhaCungCap);

        jLabel2.setText("SupplierID");

        jLabel3.setText("SupplierName");

        jLabel4.setText("ContactInfo");

        jLabel5.setText("Email");

        jLabel6.setText("Phone");

        jLabel7.setText("Address");

        tbSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbSupplierIDActionPerformed(evt);
            }
        });

        tbSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbSupplierNameActionPerformed(evt);
            }
        });

        btnThemNhaCungCap.setText("Thêm");
        btnThemNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaCungCapActionPerformed(evt);
            }
        });

        btnSuaNhaCungCap.setText("Sửa");
        btnSuaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhaCungCapActionPerformed(evt);
            }
        });

        btnXoaNhaCungCap.setText("Xóa");
        btnXoaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhaCungCapActionPerformed(evt);
            }
        });

        btnLamRongNhaCungCap.setText("Làm trỗng");
        btnLamRongNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamRongNhaCungCapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnThemNhaCungCap)
                                .addComponent(jLabel4)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tbSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tbSupplierName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tbContactInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbAddress)
                                    .addComponent(tbPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tbEmail))
                                .addContainerGap())
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSuaNhaCungCap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaNhaCungCap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamRongNhaCungCap)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tbContactInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(tbSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tbSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaNhaCungCap)
                        .addComponent(btnLamRongNhaCungCap))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemNhaCungCap)
                        .addComponent(btnSuaNhaCungCap)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhà cung cấp", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhaCungCapActionPerformed
        // TODO add your handling code here:
        // Lấy dữ liệu từ các trường nhập
        String supplierName = tbSupplierName.getText();
        String contactInfo = tbContactInfo.getText();
        String address = tbAddress.getText();
        String phone = tbPhone.getText();
        String email = tbEmail.getText();
        int createdBy = 1; // Thay đổi theo user hiện tại
        int updatedBy = 1; // Thay đổi theo user hiện tại

        // Tạo đối tượng Supplier
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierName);
        supplier.setContactInfo(contactInfo);
        supplier.setAddress(address);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setCreatedBy(createdBy);
        supplier.setUpdatedBy(updatedBy);

        // Gọi phương thức addSupplier từ SupplierController
        SupplierController supplierController = new SupplierController();
        boolean success = supplierController.addSupplier(supplier);

        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
            // Cập nhật lại bảng dữ liệu
            showPhieuBanHang();
            showPhieuNhapHang();
            showDanhMucSanPham();
            showSanPham();
            showNhaCungCap();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại!");
        }
    }//GEN-LAST:event_btnThemNhaCungCapActionPerformed

    private void tbSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSupplierIDActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_tbSupplierIDActionPerformed

    private void tbSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSupplierNameActionPerformed

    private void btnSuaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhaCungCapActionPerformed
        // TODO add your handling code here:
        try {
            // Lấy dữ liệu từ các trường nhập
            int supplierID = Integer.parseInt(tbSupplierID.getText());
            String supplierName = tbSupplierName.getText();
            String contactInfo = tbContactInfo.getText();
            String address = tbAddress.getText();
            String phone = tbPhone.getText();
            String email = tbEmail.getText();
            int updatedBy = 1; // Thay đổi theo user hiện tại

            // Tạo đối tượng Supplier
            Supplier supplier = new Supplier();
            supplier.setSupplierID(supplierID);
            supplier.setSupplierName(supplierName);
            supplier.setContactInfo(contactInfo);
            supplier.setAddress(address);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setUpdatedBy(updatedBy);

            // Gọi phương thức updateSupplier từ SupplierController
            SupplierController supplierController = new SupplierController();
            boolean success = supplierController.updateSupplier(supplier);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!");
                // Cập nhật lại bảng dữ liệu
                showNhaCungCap();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dữ liệu cho SupplierID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật nhà cung cấp.");
        }
    }//GEN-LAST:event_btnSuaNhaCungCapActionPerformed

    private void btnXoaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhaCungCapActionPerformed
        // TODO add your handling code here:
        try {
            int supplierID = Integer.parseInt(tbSupplierID.getText());

            // Gọi phương thức deleteSupplier từ SupplierController
            SupplierController supplierController = new SupplierController();
            boolean success = supplierController.deleteSupplier(supplierID);

            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
                // Cập nhật lại bảng dữ liệu
                showNhaCungCap();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dữ liệu cho SupplierID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa nhà cung cấp.");
        }
    }//GEN-LAST:event_btnXoaNhaCungCapActionPerformed

    private void btnLamRongNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamRongNhaCungCapActionPerformed
        // TODO add your handling code here:
        // Xóa nội dung của tất cả các trường nhập
        tbSupplierID.setText("");
        tbSupplierName.setText("");
        tbContactInfo.setText("");
        tbAddress.setText("");
        tbPhone.setText("");
        tbEmail.setText("");
    }//GEN-LAST:event_btnLamRongNhaCungCapActionPerformed

    private void TableNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNhaCungCapMouseClicked
        // TODO add your handling code here:
         // Lấy chỉ số hàng được chọn
    int row = TableNhaCungCap.getSelectedRow();
        // Lấy dữ liệu từ các ô trong hàng được chọn
        String supplierID = TableNhaCungCap.getValueAt(row, 0).toString();
        String supplierName = TableNhaCungCap.getValueAt(row, 1).toString();
        String contactInfo = TableNhaCungCap.getValueAt(row, 2).toString();
        String address = TableNhaCungCap.getValueAt(row, 3).toString();
        String phone = TableNhaCungCap.getValueAt(row, 4).toString();
        String email = TableNhaCungCap.getValueAt(row, 5).toString();

        // Hiển thị thông tin trong các ô nhập liệu hoặc cửa sổ thông tin chi tiết
        // Ví dụ:
        tbSupplierID.setText(supplierID);
        tbSupplierName.setText(supplierName);
        tbContactInfo.setText(contactInfo);
        tbAddress.setText(address);
        tbPhone.setText(phone);
        tbEmail.setText(email);
    }//GEN-LAST:event_TableNhaCungCapMouseClicked

    private void tbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenSanPhamActionPerformed

    private void tbDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDonGiaActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // Lấy thông tin về sản phẩm từ các trường nhập liệu trên giao diện
        String productName = tbTenSanPham.getText();
        String category = tbDanhMuc.getText();
        int stockQuantity = Integer.parseInt(tbSoLuong.getText());
        double unitPrice = Double.parseDouble(tbDonGia.getText());

        // Lấy thông tin về nhà cung cấp từ combobox hoặc trường nhập liệu tương ứng
        String supplierName = (String) ComboBoxNhaCungCap.getSelectedItem(); // Lấy tên nhà cung cấp từ combobox

        // Lấy thông tin về hình ảnh sản phẩm từ LabelAnhSanPham
        String imageUrl = LabelAnhSanPham.getText();

        // Lấy ID của nhà cung cấp từ tên nhà cung cấp
        SupplierController supplierController = new SupplierController();
        Supplier supplier = supplierController.getSupplierByName(supplierName);
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin về nhà cung cấp.");
            return;
        }
        int supplierID = supplier.getSupplierID();

        // Tạo một đối tượng ProductImage từ đường dẫn của hình ảnh
        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(imageUrl);

        // Tạo một đối tượng Product từ thông tin vừa lấy được
        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(category);
        product.setStockQuantity(stockQuantity);
        product.setUnitPrice(unitPrice);

        // Gọi phương thức addProduct trong ProductController để thêm sản phẩm và hình ảnh vào cơ sở dữ liệu
        ProductController productController = new ProductController();
        boolean success = productController.addProduct(product, productImage, supplierID);

        // Kiểm tra xem việc thêm sản phẩm có thành công không và hiển thị thông báo tương ứng
        if (success) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm sản phẩm!");
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnLamTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamTrongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamTrongActionPerformed

    private void btnChonAnhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhSanPhamActionPerformed
        // Mở hộp thoại để chọn tệp hình ảnh
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn Ảnh Sản Phẩm"); // Thiết lập tiêu đề cho hộp thoại
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Chỉ cho phép chọn tệp

        // Hiển thị hộp thoại chọn tệp
        int result = fileChooser.showOpenDialog(this);

        // Kiểm tra xem người dùng đã chọn tệp hay chưa
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn đến tệp hình ảnh đã chọn
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            // Hiển thị đường dẫn đến hình ảnh trên nhãn
            LabelAnhSanPham.setText(imagePath);
        }
    }//GEN-LAST:event_btnChonAnhSanPhamActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLyHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNhaCungCap;
    private javax.swing.JLabel LabelAnhSanPham;
    private javax.swing.JTable TableDanhMucSanPham;
    private javax.swing.JTable TableNhaCungCap;
    private javax.swing.JTable TablePhieuBanHang;
    private javax.swing.JTable TablePhieuNhapHang;
    private javax.swing.JTable TableSanPham;
    private javax.swing.JButton btnChonAnhSanPham;
    private javax.swing.JButton btnLamRongNhaCungCap;
    private javax.swing.JButton btnLamTrong;
    private javax.swing.JButton btnSuaNhaCungCap;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnThemNhaCungCap;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnXoaNhaCungCap;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tbAddress;
    private javax.swing.JTextField tbContactInfo;
    private javax.swing.JTextField tbDanhMuc;
    private javax.swing.JTextField tbDonGia;
    private javax.swing.JTextField tbEmail;
    private javax.swing.JTextField tbMaSanPham;
    private javax.swing.JTextField tbPhone;
    private javax.swing.JTextField tbSoLuong;
    private javax.swing.JTextField tbSupplierID;
    private javax.swing.JTextField tbSupplierName;
    private javax.swing.JTextField tbTenSanPham;
    // End of variables declaration//GEN-END:variables
}
