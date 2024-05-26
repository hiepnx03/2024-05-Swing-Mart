/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.views;

import org.example.controllers.*;
import org.example.models.Product;
import org.example.models.SalesReceipt;
import org.example.models.SalesReceiptDetail;
import org.example.util.ImageRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author hiepn
 */

public class FormGiaoDich extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private InventoryReceiptController inventoryReceiptController;
    private SalesReceiptController salesReceiptController;
    private SalesReceiptDetailController salesReceiptDetailController;
    private ProductController productController;
    private SupplierController supplierController;
    private int loggedInUserID;
    private int employeeID; // Thêm biến để lưu trữ EmployeeID

    /**
     * Creates new form FormCungUng
     */
    public FormGiaoDich(int loggedInUserID) {
        initComponents();
        this.loggedInUserID = loggedInUserID;

        inventoryReceiptController = new InventoryReceiptController();
        salesReceiptController = new SalesReceiptController();
        salesReceiptDetailController = new SalesReceiptDetailController();
        productController = new ProductController();
        supplierController = new SupplierController();
//        showPhieuBanHang();
//        showPhieuNhapHang();
        // Lấy thông tin EmployeeID từ UserController
        UserController userController = new UserController();
        employeeID = userController.getEmployeeIDByUserID(loggedInUserID);
        System.out.println("EmployeeID: " + employeeID);
        showChiTietBienLaiBanHang();
        showSanPham();
        showBienLaiBanHang();
        addEscapeKeyBinding();
        System.out.println("Đang Quản Lý Giao Dịch Với UserID: " + loggedInUserID); // In giá trị loggedInUserID

        tbSoLuong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });
    }

    private void showBienLaiBanHang() {
        salesReceiptController = new SalesReceiptController();
        tableModel = salesReceiptController.getAllPhieuBanHang();
        TableBienLaiBanHang.setModel(tableModel);
    }

    private void showChiTietBienLaiBanHang() {
        salesReceiptDetailController = new SalesReceiptDetailController();
        tableModel = salesReceiptDetailController.getAllSalesReceiptDetail();
        TableChiTietBienLaiBanHang.setModel(tableModel);
    }

    private void addEscapeKeyBinding() {
        String escapeAction = "escapeAction";
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        Action escapeActionHandler = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Đăng xuất", "Thoát", "Không"};
                int response = JOptionPane.showOptionDialog(
                        FormGiaoDich.this,
                        "Bạn có muốn đăng xuất hay thoát chương trình?",
                        "Xác nhận",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Bộ phận quản lý giao dịch đã đăng xuất");
                    // Xử lý đăng xuất
                    btnDangXuatActionPerformed(null);
                } else if (response == JOptionPane.NO_OPTION) {
                    // Thoát chương trình
                    System.out.println("Đã thoát chương trình");
                    System.exit(0);
                } else if (response == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Không có gì");
                }
            }
        };

        JRootPane rootPane = this.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, escapeAction);
        rootPane.getActionMap().put(escapeAction, escapeActionHandler);
    }

    private void showSanPham() {
        productController = new ProductController();
        tableModel = productController.getAllProductGiaoDichDetails();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableChiTietBienLaiBanHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableBienLaiBanHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tbTongGiaTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbSoLuong = new javax.swing.JTextField();
        ComboBoxEmployeeID = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        tbTenSanPham = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tbMaSanPham = new javax.swing.JTextField();
        tbGiaTien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnGiaoDich = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tbTenKhachHang = new javax.swing.JTextField();
        tbPhuongThucThanhToan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableSanPham.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
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
        TableSanPham.setRowHeight(75);
        TableSanPham.setSelectionBackground(new java.awt.Color(51, 153, 255));
        TableSanPham.setShowHorizontalLines(true);
        TableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel3);

        TableChiTietBienLaiBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TableChiTietBienLaiBanHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chi tiết biên lai bán hàng", jPanel2);

        TableBienLaiBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TableBienLaiBanHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biên lai bán hàng", jPanel4);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setText("Bộ Phận Giao dịch");

        btnDangXuat.setBackground(new java.awt.Color(220, 53, 69));
        btnDangXuat.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        jLabel4.setText("Tổng giá tiền");

        tbTongGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTongGiaTienActionPerformed(evt);
            }
        });

        jLabel5.setText("Số lượng");

        tbSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbSoLuongActionPerformed(evt);
            }
        });

        ComboBoxEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEmployeeIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(tbTongGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBoxEmployeeID, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tbSoLuong))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tbTongGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenSanPhamActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên sản phẩm");

        jLabel6.setText("Mã sản phẩm");

        tbMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbMaSanPhamActionPerformed(evt);
            }
        });

        tbGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbGiaTienActionPerformed(evt);
            }
        });

        jLabel3.setText("Giá tiền");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbMaSanPham)
                            .addComponent(tbTenSanPham)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(tbGiaTien)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(tbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGiaoDich.setBackground(new java.awt.Color(0, 123, 255));
        btnGiaoDich.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGiaoDich.setForeground(new java.awt.Color(255, 255, 255));
        btnGiaoDich.setText("Giao dịch");
        btnGiaoDich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoDichActionPerformed(evt);
            }
        });

        jLabel7.setText("Tên khách hàng");

        jLabel8.setText("Phương thức thanh toán");

        tbTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenKhachHangActionPerformed(evt);
            }
        });

        tbPhuongThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbPhuongThucThanhToanActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDangXuat)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tbTenKhachHang))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(tbPhuongThucThanhToan)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGiaoDich, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDangXuat)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnGiaoDich, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tbTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(tbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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

    private void TableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSanPhamMouseClicked
        // TODO add your handling code here:
        int selectedRow = TableSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            String maSanPham = TableSanPham.getValueAt(selectedRow, 0).toString();
            String tenSanPham = TableSanPham.getValueAt(selectedRow, 1).toString();
            String giaTien = TableSanPham.getValueAt(selectedRow, 2).toString(); // Adjust the column index as needed
//            String soLuong = TableSanPham.getValueAt(selectedRow, 3).toString(); // Adjust the column index as needed

            tbMaSanPham.setText(maSanPham);
            tbTenSanPham.setText(tenSanPham);
            tbGiaTien.setText(giaTien);
//            tbSoLuong.setText(soLuong); // Set the quantity

            // Thêm dữ liệu vào bảng chi tiết biên lai bán hàng
            DefaultTableModel chiTietModel = (DefaultTableModel) TableChiTietBienLaiBanHang.getModel();
            chiTietModel.addRow(new Object[]{maSanPham, tenSanPham, giaTien}); // Add quantity to details table
        }
    }//GEN-LAST:event_TableSanPhamMouseClicked

    private void tbSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSoLuongActionPerformed
        try {
            int productID = Integer.parseInt(tbMaSanPham.getText()); // Lấy ProductID từ giao diện
            int soLuong = Integer.parseInt(tbSoLuong.getText());

            if (soLuong < 0) {
                throw new NumberFormatException("Số lượng không thể âm");
            }

            // Kiểm tra số lượng tồn kho
            int stockQuantity = productController.getStockQuantity(productID);
            if (soLuong > stockQuantity) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập vào lớn hơn số lượng tồn kho hiện tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                tbSoLuong.setText("");
                return;
            }

            // Lấy đơn giá từ cơ sở dữ liệu thông qua ProductController
            double unitPrice = productController.getUnitPrice(productID);
            double tongGiaTien = unitPrice * soLuong;
            System.out.println(unitPrice);
            System.out.println(tongGiaTien);

            // Hiển thị tổng giá tiền
            tbTongGiaTien.setText(String.valueOf(tongGiaTien));

            // Xử lý logic khi số lượng hợp lệ
            System.out.println("Số lượng hợp lệ: " + soLuong);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên hợp lệ cho số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            tbSoLuong.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbSoLuongActionPerformed

    private void updateTotalPrice() {
        try {
            String productIdText = tbMaSanPham.getText().trim();
            String quantityText = tbSoLuong.getText().trim();

            if (productIdText.isEmpty() || quantityText.isEmpty()) {
                tbTongGiaTien.setText("0"); // Set total price to 0 if either product ID or quantity is empty
                return;
            }

            int productID = Integer.parseInt(productIdText);
            int soLuong = Integer.parseInt(quantityText);

            if (soLuong < 0) {
                throw new NumberFormatException("Số lượng không thể âm");
            }

            // Kiểm tra số lượng tồn kho
            int stockQuantity = productController.getStockQuantity(productID);
            if (soLuong > stockQuantity) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Số lượng nhập vào lớn hơn số lượng tồn kho hiện tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    tbSoLuong.setText("0"); // Set quantity to 0 if quantity is greater than stock quantity
                    tbTongGiaTien.setText("0"); // Set total price to 0 if quantity is greater than stock quantity
                });
                return;
            }

            // Lấy đơn giá từ cơ sở dữ liệu thông qua ProductController
            double unitPrice = productController.getUnitPrice(productID);
            double tongGiaTien = unitPrice * soLuong;

            // Hiển thị tổng giá tiền
            tbTongGiaTien.setText(String.valueOf(tongGiaTien));

            // Xử lý logic khi số lượng hợp lệ
            System.out.println("Số lượng hợp lệ: " + soLuong);
        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên hợp lệ cho số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                tbSoLuong.setText("0"); // Set quantity to 0 if there's a number format exception
                tbTongGiaTien.setText("0"); // Set total price to 0 if there's a number format exception
            });
        } catch (SQLException e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                tbSoLuong.setText("0"); // Set quantity to 0 if there's an SQL exception
                tbTongGiaTien.setText("0"); // Set total price to 0 if there's an SQL exception
            });
        }
    }


    private void tbTongGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTongGiaTienActionPerformed
        // TODO add your handling code here:
        // Đặt TextBox tbExample thành không thể chỉnh sửa
        tbTongGiaTien.setEditable(false);

        // Hoặc, nếu bạn muốn hoàn toàn vô hiệu hóa TextBox để ngăn người dùng tương tác với nó
        tbTongGiaTien.setEnabled(false);

        try {
            double tongGiaTien = Double.parseDouble(tbTongGiaTien.getText());
            if (tongGiaTien < 0) {
                throw new NumberFormatException("Tổng giá tiền không thể âm");
            }
            // Xử lý logic khi tổng giá tiền hợp lệ
            System.out.println("Tổng giá tiền hợp lệ: " + tongGiaTien);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số thập phân hợp lệ cho tổng giá tiền.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            tbTongGiaTien.setText("");
        }
    }//GEN-LAST:event_tbTongGiaTienActionPerformed

    private void tbGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbGiaTienActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        // Đặt TextBox tbExample thành không thể chỉnh sửa

        // Hoặc, nếu bạn muốn hoàn toàn vô hiệu hóa TextBox để ngăn người dùng tương tác với nó

    }//GEN-LAST:event_tbGiaTienActionPerformed

    private void tbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTenSanPhamActionPerformed
        // TODO add your handling code here:

        // Hoặc, nếu bạn muốn hoàn toàn vô hiệu hóa TextBox để ngăn người dùng tương tác với nó
    }//GEN-LAST:event_tbTenSanPhamActionPerformed

    private void tbMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbMaSanPhamActionPerformed
        // TODO add your handling code here:
        // Hoặc, nếu bạn muốn hoàn toàn vô hiệu hóa TextBox để ngăn người dùng tương tác với nó
    }//GEN-LAST:event_tbMaSanPhamActionPerformed

    private void btnGiaoDichActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String productIdText = tbMaSanPham.getText().trim();
            String quantityText = tbSoLuong.getText().trim();
            String tenKhachHang = tbTenKhachHang.getText().trim(); // Lấy tên khách hàng từ giao diện
            String phuongThucThanhToan = tbPhuongThucThanhToan.getText().trim(); // Lấy phương thức thanh toán từ giao diện

            if (productIdText.isEmpty() || quantityText.isEmpty() || tenKhachHang.isEmpty() || phuongThucThanhToan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm, số lượng, tên khách hàng và phương thức thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int productID = Integer.parseInt(productIdText);
            int soLuong = Integer.parseInt(quantityText);

            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không thể âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra số lượng tồn kho
            int stockQuantity = productController.getStockQuantity(productID);
            if (soLuong > stockQuantity) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập vào lớn hơn số lượng tồn kho hiện tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy đơn giá từ cơ sở dữ liệu thông qua ProductController
            double unitPrice = productController.getUnitPrice(productID);
            double tongGiaTien = unitPrice * soLuong;

            // Tạo chi tiết phiếu bán hàng
            SalesReceiptDetail salesReceiptDetail = new SalesReceiptDetail();
            salesReceiptDetail.setProductID(productID);
            salesReceiptDetail.setQuantity(soLuong);
            salesReceiptDetail.setUnitPrice(unitPrice);
            salesReceiptDetail.setTotalPrice(tongGiaTien);

            // Thực hiện giao dịch
            boolean result = productController.giaoDichSanPham(salesReceiptDetail, tenKhachHang, phuongThucThanhToan, employeeID, loggedInUserID);

            if (result) {
                JOptionPane.showMessageDialog(this, "Giao dịch thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Cập nhật lại tổng giá tiền hoặc các thông tin khác nếu cần
                showChiTietBienLaiBanHang();
                showBienLaiBanHang();
                showSanPham();
            } else {
                JOptionPane.showMessageDialog(this, "Giao dịch thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên hợp lệ cho số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }                                           

    private void ComboBoxEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEmployeeIDActionPerformed

    private void tbTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTenKhachHangActionPerformed
        // TODO add your handling code here:
        String tenKhachHang = tbTenKhachHang.getText().trim();
        // You can perform actions based on the customer name entered by the user
        System.out.println("Customer name entered: " + tenKhachHang);
    }//GEN-LAST:event_tbTenKhachHangActionPerformed

    private void tbPhuongThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbPhuongThucThanhToanActionPerformed
        // TODO add your handling code here:
        String phuongThucThanhToan = tbPhuongThucThanhToan.getText().trim();
        // You can perform actions based on the payment method entered by the user
        System.out.println("Payment method entered: " + phuongThucThanhToan);
    }//GEN-LAST:event_tbPhuongThucThanhToanActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        FormDangNhap loginForm = new FormDangNhap(); // Assuming LoginForm is your login screen
        loginForm.setVisible(true);
        this.dispose(); // Close the current window
    }//GEN-LAST:event_btnDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(FormGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormGiaoDich(loggedInUserID[0]).setVisible(true);
                System.out.println(loggedInUserID[0]);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxEmployeeID;
    private javax.swing.JTable TableBienLaiBanHang;
    private javax.swing.JTable TableChiTietBienLaiBanHang;
    private javax.swing.JTable TableSanPham;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnGiaoDich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tbGiaTien;
    private javax.swing.JTextField tbMaSanPham;
    private javax.swing.JTextField tbPhuongThucThanhToan;
    private javax.swing.JTextField tbSoLuong;
    private javax.swing.JTextField tbTenKhachHang;
    private javax.swing.JTextField tbTenSanPham;
    private javax.swing.JTextField tbTongGiaTien;
    // End of variables declaration//GEN-END:variables
}
