package org.example.controllers;

import org.example.services.SalesReceiptDetailService;
import org.example.services.impl.SalesReceiptDetailServiceImpl;

import javax.swing.table.DefaultTableModel;

public class SalesReceiptDetailController {

    private final SalesReceiptDetailService salesReceiptDetailService;

    public SalesReceiptDetailController() {
        this.salesReceiptDetailService = new SalesReceiptDetailServiceImpl();
    }

    public DefaultTableModel getAllSalesReceiptDetail() {
        return salesReceiptDetailService.getAllSalesReceiptDetail();
    }
}
