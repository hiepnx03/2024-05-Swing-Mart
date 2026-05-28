package org.example.controllers;

import org.example.services.SalesReceiptService;
import org.example.services.impl.SalesReceiptServiceImpl;

import javax.swing.table.DefaultTableModel;

public class SalesReceiptController {

    private final SalesReceiptService salesReceiptService;

    public SalesReceiptController() {
        this.salesReceiptService = new SalesReceiptServiceImpl();
    }

    public DefaultTableModel getAllPhieuBanHang() {
        return salesReceiptService.getAllPhieuBanHang();
    }
}
