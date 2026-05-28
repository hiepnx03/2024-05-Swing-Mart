package org.example.services.impl;

import org.example.repositories.InventoryReceiptRepository;
import org.example.repositories.impl.InventoryReceiptRepositoryImpl;
import org.example.services.InventoryReceiptService;

import javax.swing.table.DefaultTableModel;

public class InventoryReceiptServiceImpl implements InventoryReceiptService {

    private final InventoryReceiptRepository inventoryReceiptRepository = new InventoryReceiptRepositoryImpl();

    @Override
    public DefaultTableModel getAllInventoryReceipts() {
        return inventoryReceiptRepository.getAllInventoryReceipts();
    }
}
