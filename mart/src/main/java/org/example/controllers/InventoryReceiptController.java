package org.example.controllers;

import org.example.services.InventoryReceiptService;
import org.example.services.impl.InventoryReceiptServiceImpl;

import javax.swing.table.DefaultTableModel;

public class InventoryReceiptController {

    private final InventoryReceiptService inventoryReceiptService;

    public InventoryReceiptController() {
        this.inventoryReceiptService = new InventoryReceiptServiceImpl();
    }

    public DefaultTableModel getAllInventoryReceipts() {
        return inventoryReceiptService.getAllInventoryReceipts();
    }
}
