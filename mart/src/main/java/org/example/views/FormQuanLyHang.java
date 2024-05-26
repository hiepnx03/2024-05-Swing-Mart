/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.views;

import org.example.controllers.*;
import org.example.models.*;
import org.example.util.ImageRenderer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

/**
 * @author hiepn
 */
// Định nghĩa class ImageRenderer



public class FormQuanLyHang extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private InventoryReceiptController inventoryReceiptController;
    private SalesReceiptController salesReceiptController;
    private ProductController productController;
    private SupplierController supplierController;
    private int loggedInUserID;

    /**
     * Creates new form FormQuanLyHang
     */
    public FormQuanLyHang(int loggedInUserID) {
        initComponents();
        this.loggedInUserID = loggedInUserID;

        // Initialize controllers
        inventoryReceiptController = new InventoryReceiptController();
        salesReceiptController = new SalesReceiptController();
        productController = new ProductController();
        supplierController = new SupplierController();
        // Load data

        showSanPham();
        showNhaCungCap();
        loadSupplier();
        addEscapeKeyBinding();
        System.out.println("Đang Quản Lý Hàng Với UserID: " + loggedInUserID); // In giá trị loggedInUserID
    }

    private void addEscapeKeyBinding() {
        String escapeAction = "escapeAction";
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        Action escapeActionHandler = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Đăng xuất", "Thoát", "Không"};
                int response = JOptionPane.showOptionDialog(
                        FormQuanLyHang.this,
                        "Bạn có muốn đăng xuất hay thoát chương trình?",
                        "Xác nhận",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Bộ phận quản lý hàng đã đăng xuất");
                    // Xử lý đăng xuất
                    btnDangXuatActionPerformed(null);
                } else if (response == JOptionPane.NO_OPTION) {
                    // Thoát chương trình
                    System.out.println("Đã thoát chương trình");
                    System.exit(0);
                }else if (response == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Không có gì");
                }
            }
        };

        JRootPane rootPane = this.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, escapeAction);
        rootPane.getActionMap().put(escapeAction, escapeActionHandler);
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) TableSanPham.getModel();
        model.fireTableDataChanged();
        updateImageColumn();
    }


    // Cập nhật hiển thị của ảnh trong bảng khi có thay đổi dữ liệu
    public void updateImageColumn() {
        TableColumnModel columnModel = TableSanPham.getColumnModel();
        TableColumn imageColumn = columnModel.getColumn(11); // Assuming column index of the image column is 11
        ImageRenderer imageRenderer = new ImageRenderer();
        imageColumn.setCellRenderer( imageRenderer);
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


    private void showNhaCungCap() {
        supplierController = new SupplierController();
        tableModel = supplierController.getAllSuppliers();
        TableNhaCungCap.setModel(tableModel);

    }

    private void showSanPham() {
        productController = new ProductController();
        tableModel = productController.getAllProductDetails();
        TableSanPham.setModel(tableModel);
        ImageRenderer imageRenderer = new ImageRenderer();
        TableSanPham.getColumnModel().getColumn(11).setCellRenderer(imageRenderer);

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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableSanPham = new javax.swing.JTable();
        btnThemSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnLamTrong = new javax.swing.JButton();
        btnChonAnhSanPham = new javax.swing.JButton();
        LabelAnhSanPham = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        ComboBoxNhaCungCap = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        tbDonGia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tbSoLuong = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        tbTenSanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tbDanhMuc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tbMaSanPham = new javax.swing.JTextField();
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
        btnDangXuat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Quản Lý Hàng");

        TableSanPham.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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
        TableSanPham.setAlignmentX(2.0F);
        TableSanPham.setAlignmentY(2.0F);
        TableSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableSanPham.setDoubleBuffered(true);
        TableSanPham.setRowHeight(50);
        TableSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        TableSanPham.setShowGrid(false);
        TableSanPham.setShowHorizontalLines(true);
        TableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableSanPham);

        btnThemSanPham.setBackground(new java.awt.Color(108, 117, 125));
        btnThemSanPham.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setText("Thêm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setBackground(new java.awt.Color(0, 123, 255));
        btnSuaSanPham.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSuaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaSanPham.setText("Sửa");
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setBackground(new java.awt.Color(220, 53, 69));
        btnXoaSanPham.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSanPham.setText("Xóa");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnLamTrong.setBackground(new java.awt.Color(0, 204, 204));
        btnLamTrong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLamTrong.setForeground(new java.awt.Color(255, 255, 255));
        btnLamTrong.setText("Làm Trỗng");
        btnLamTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamTrongActionPerformed(evt);
            }
        });

        btnChonAnhSanPham.setBackground(new java.awt.Color(102, 153, 255));
        btnChonAnhSanPham.setText("Chọn ảnh");
        btnChonAnhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhSanPhamActionPerformed(evt);
            }
        });

        LabelAnhSanPham.setText("Ảnh Sản Phẩm");

        jLabel13.setText("Nhà cung cấp");

        tbDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbDonGiaActionPerformed(evt);
            }
        });

        jLabel12.setText("Đơn giá");

        jLabel11.setText("Số lượng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel12))
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbDonGia)
                    .addComponent(tbSoLuong)
                    .addComponent(ComboBoxNhaCungCap, 0, 132, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ComboBoxNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        tbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenSanPhamActionPerformed(evt);
            }
        });

        jLabel9.setText("Tên sản phẩm");

        jLabel10.setText("Danh mục");

        jLabel8.setText("Mã sản phẩm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(tbDanhMuc))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbMaSanPham)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnThemSanPham)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSuaSanPham)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoaSanPham)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLamTrong))
                                    .addComponent(btnChonAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnChonAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSanPham)
                            .addComponent(btnSuaSanPham)
                            .addComponent(btnXoaSanPham)
                            .addComponent(btnLamTrong))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý sản phẩm", jPanel5);

        TableNhaCungCap.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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
        TableNhaCungCap.setRowHeight(50);
        TableNhaCungCap.setSelectionBackground(new java.awt.Color(232, 57, 95));
        TableNhaCungCap.setShowHorizontalLines(true);
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

        btnThemNhaCungCap.setBackground(new java.awt.Color(108, 117, 125));
        btnThemNhaCungCap.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnThemNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNhaCungCap.setText("Thêm");
        btnThemNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaCungCapActionPerformed(evt);
            }
        });

        btnSuaNhaCungCap.setBackground(new java.awt.Color(0, 123, 255));
        btnSuaNhaCungCap.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSuaNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNhaCungCap.setText("Sửa");
        btnSuaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhaCungCapActionPerformed(evt);
            }
        });

        btnXoaNhaCungCap.setBackground(new java.awt.Color(220, 53, 69));
        btnXoaNhaCungCap.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnXoaNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNhaCungCap.setText("Xóa");
        btnXoaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhaCungCapActionPerformed(evt);
            }
        });

        btnLamRongNhaCungCap.setBackground(new java.awt.Color(0, 204, 204));
        btnLamRongNhaCungCap.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLamRongNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
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
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhà cung cấp", jPanel6);

        btnDangXuat.setBackground(new java.awt.Color(220, 53, 69));
        btnDangXuat.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnDangXuat)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        FormDangNhap loginForm = new FormDangNhap(); // Assuming LoginForm is your login screen
        loginForm.setVisible(true);
        this.dispose(); // Close the current window
    }//GEN-LAST:event_btnDangXuatActionPerformed

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

                showSanPham();
                showNhaCungCap();
                loadSupplier();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xóa nhà cung cấp vì nhà cung cấp có sản phẩm liên quan !");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dữ liệu cho SupplierID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa nhà cung cấp.");
        }
      
        showSanPham();
        showNhaCungCap();
        loadSupplier();
    }//GEN-LAST:event_btnXoaNhaCungCapActionPerformed

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

                showSanPham();
                showNhaCungCap();
                loadSupplier();
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

            showSanPham();
            showNhaCungCap();
            loadSupplier();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại!");
        }
    }//GEN-LAST:event_btnThemNhaCungCapActionPerformed

    private void tbSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSupplierNameActionPerformed

    private void tbSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSupplierIDActionPerformed

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
            String fileName = selectedFile.getName();

            // Kiểm tra xem tên tệp có trùng lặp không
            ProductController productController = new ProductController();
            boolean fileNameExists = productController.checkFileNameExists(fileName);

            if (fileNameExists) {
                String newFileName = fileName;
                String[] fileParts = fileName.split("\\.");
                int counter = 1;

                while (fileNameExists) {
                    newFileName = fileParts[0] + "_" + counter + "." + fileParts[1];
                    fileNameExists = productController.checkFileNameExists(newFileName);
                    counter++;
                }

                // Hiển thị tên tệp mới trên nhãn
                LabelAnhSanPham.setText(newFileName);

                // Cập nhật lại đường dẫn đến tệp đã chọn
                String imagePath = selectedFile.getAbsolutePath();
                File newSelectedFile = new File("src/main/resources/images/" + newFileName);

                try {
                    Files.copy(selectedFile.toPath(), newSelectedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi sao chép tệp ảnh: " + e.getMessage());
                }
            } else {
                LabelAnhSanPham.setText(fileName);

                try {
                    File destDir = new File("src/main/resources/images");
                    if (!destDir.exists()) {
                        destDir.mkdirs();
                    }

                    File destFile = new File(destDir, fileName);
                    Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi sao chép tệp ảnh: " + e.getMessage());
                }
            }

            // Gọi phương thức refreshTable để cập nhật lại bảng và hình ảnh
        }
    }//GEN-LAST:event_btnChonAnhSanPhamActionPerformed

    private void btnLamTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamTrongActionPerformed
        // TODO add your handling code here:
        // Xóa nội dung trong các trường nhập liệu
        tbTenSanPham.setText("");
        tbDanhMuc.setText("");
        tbSoLuong.setText("");
        tbDonGia.setText("");
        ComboBoxNhaCungCap.setSelectedIndex(0);
        LabelAnhSanPham.setText("");
    }//GEN-LAST:event_btnLamTrongActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
        try {
            int productID = Integer.parseInt(tbMaSanPham.getText());

            // Gọi phương thức deleteProduct từ ProductController
            ProductController productController = new ProductController();
            boolean success = productController.deleteProduct(productID);

            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                // Cập nhật lại bảng dữ liệu

                showSanPham();
                showNhaCungCap();
                showSanPham();
                refreshTable();
            } else {

                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dữ liệu cho ProductID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa sản phẩm.");
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        // Lấy ID sản phẩm cần sửa từ bảng
        int selectedRow = TableSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa.");
            return;
        }
        int productID = (int) TableSanPham.getValueAt(selectedRow, 0); // Assuming productID is in the first column

        // Lấy thông tin mới của sản phẩm từ các trường nhập liệu
        String productName = tbTenSanPham.getText();
        String category = tbDanhMuc.getText();
        int stockQuantity = Integer.parseInt(tbSoLuong.getText());
        double unitPrice = Double.parseDouble(tbDonGia.getText());
        String supplierName = (String) ComboBoxNhaCungCap.getSelectedItem();
        String imageUrl = LabelAnhSanPham.getText();

        // Lấy ID của nhà cung cấp từ tên nhà cung cấp
        SupplierController supplierController = new SupplierController();
        Supplier supplier = supplierController.getSupplierByName(supplierName);
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin về nhà cung cấp.");
            return;
        }
        int supplierID = supplier.getSupplierID();

        // Tạo đối tượng ProductImage từ đường dẫn của hình ảnh
        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(imageUrl);

        // Tạo đối tượng Product với thông tin mới
        Product product = new Product();
        product.setProductID(productID);
        product.setProductName(productName);
        product.setCategory(category);
        product.setStockQuantity(stockQuantity);
        product.setUnitPrice(unitPrice);

        // Sử dụng ID người dùng đã đăng nhập
        int userID = loggedInUserID;
        if (userID == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập trước khi sửa sản phẩm.");
            return;
        }

        // Gọi phương thức updateProduct từ ProductController
        ProductController productController = new ProductController();
        boolean success = productController.updateProduct(product, productImage, supplierID, userID);

        // Hiển thị thông báo tương ứng
        if (success) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã được cập nhật thành công!");
            // Cập nhật lại dữ liệu trong bảng
            showSanPham();

            showNhaCungCap();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật sản phẩm!");
        }
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

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

        // Sử dụng giá trị loggedInUserID đã được lưu trong lớp FormDangNhap
        int userID = loggedInUserID;

        if (userID == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập trước khi thực hiện thêm sản phẩm." + loggedInUserID);
            return;
        }

        // Gọi phương thức addProduct trong ProductController để thêm sản phẩm và hình ảnh vào cơ sở dữ liệu
        ProductController productController = new ProductController();
        boolean success = productController.addProduct(product, productImage, supplierID, userID);

        // Kiểm tra xem việc thêm sản phẩm có thành công không và hiển thị thông báo tương ứng
        if (success) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm thành công!");

            showSanPham();
            showNhaCungCap();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm sản phẩm!");
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void TableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSanPhamMouseClicked
        int selectedRow = TableSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) TableSanPham.getModel();
            String fileName = (String) model.getValueAt(selectedRow, 11); // Lấy giá trị của cột ImageUrl

            // Lấy thông tin từ dòng được chọn
            int productID = (int) model.getValueAt(selectedRow, 0);
            String productName = (String) model.getValueAt(selectedRow, 1);
            String category = (String) model.getValueAt(selectedRow, 2);
            int stockQuantity = (int) model.getValueAt(selectedRow, 3);
            double unitPrice = (double) model.getValueAt(selectedRow, 4);
            String supplierName = (String) model.getValueAt(selectedRow, 5);

            // Hiển thị thông tin sản phẩm trên giao diện
            tbMaSanPham.setText(String.valueOf(productID));
            tbTenSanPham.setText(productName);
            tbDanhMuc.setText(category);
            tbSoLuong.setText(String.valueOf(stockQuantity));
            tbDonGia.setText(String.valueOf(unitPrice));
            ComboBoxNhaCungCap.setSelectedItem(supplierName);

            String imagePath = "src/main/resources/images/" + fileName;
            File imgFile1 = new File(imagePath);
            if (imgFile1.exists()) {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                LabelAnhSanPham.setIcon(new ImageIcon(image));
                LabelAnhSanPham.setText(fileName);
            } else {
                LabelAnhSanPham.setIcon(null);
                LabelAnhSanPham.setText("Ảnh không tồn tại");
            }
        }
    }//GEN-LAST:event_TableSanPhamMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        final int[] loggedInUserID = {0}; // Khởi tạo với một giá trị mặc định

        // Thử lấy ID của người dùng đã đăng nhập từ FormDangNhap
        try {
            FormDangNhap form = new FormDangNhap();
            loggedInUserID[0] = form.getLoggedInUserID(); // Gọi phương thức không tĩnh trên một thể hiện
        } catch (Exception e) {
            // Xử lý trường hợp người dùng chưa đăng nhập
            System.out.println("Người dùng chưa đăng nhập. Vui lòng đăng nhập trước.");
            // Có thể chuyển hướng người dùng đến form đăng nhập hoặc xử lý theo logic của ứng dụng
            return; // Kết thúc chương trình hoặc thực hiện bất kỳ hành động cần thiết khác
        }

        // Kiểm tra giá trị của loggedInUserID trước khi sử dụng
        if (loggedInUserID[0] == 0) {
            System.out.println("Người dùng chưa đăng nhập. Vui lòng đăng nhập trước.");
            return;
        }
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
                new FormQuanLyHang(loggedInUserID[0]).setVisible(true);
                System.out.println(loggedInUserID[0]);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNhaCungCap;
    private javax.swing.JLabel LabelAnhSanPham;
    private javax.swing.JTable TableNhaCungCap;
    private javax.swing.JTable TableSanPham;
    private javax.swing.JButton btnChonAnhSanPham;
    private javax.swing.JButton btnDangXuat;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
