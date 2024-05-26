/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.views;

import org.example.controllers.*;
import org.example.util.ImageRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
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
        showChiTietBienLaiBanHang();
        showSanPham();
        showBienLaiBanHang();
        addEscapeKeyBinding();
        System.out.println("Đang Quản Lý Giao Dịch Với UserID: " + loggedInUserID); // In giá trị loggedInUserID
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
                }else if (response == JOptionPane.CANCEL_OPTION) {
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
        jScrollPane1.setViewportView(TableSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
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

        jLabel1.setText("Giao dịch");

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
                .addGap(117, 117, 117)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDangXuat)
                .addGap(62, 62, 62))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDangXuat)
                        .addGap(1, 1, 1)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JTable TableBienLaiBanHang;
    private javax.swing.JTable TableChiTietBienLaiBanHang;
    private javax.swing.JTable TableSanPham;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
