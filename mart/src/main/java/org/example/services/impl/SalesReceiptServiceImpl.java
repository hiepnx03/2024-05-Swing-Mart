package org.example.services.impl;

import org.example.repositories.SalesReceiptRepository;
import org.example.repositories.impl.SalesReceiptRepositoryImpl;
import org.example.services.SalesReceiptService;

import javax.swing.table.DefaultTableModel;

public class SalesReceiptServiceImpl implements SalesReceiptService {

    private final SalesReceiptRepository salesReceiptRepository = new SalesReceiptRepositoryImpl();

    @Override
    public DefaultTableModel getAllPhieuBanHang() {
        return salesReceiptRepository.getAllPhieuBanHang();
    }
}
