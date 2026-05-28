package org.example.services.impl;

import org.example.repositories.SalesReceiptDetailRepository;
import org.example.repositories.impl.SalesReceiptDetailRepositoryImpl;
import org.example.services.SalesReceiptDetailService;

import javax.swing.table.DefaultTableModel;

public class SalesReceiptDetailServiceImpl implements SalesReceiptDetailService {

    private final SalesReceiptDetailRepository salesReceiptDetailRepository = new SalesReceiptDetailRepositoryImpl();

    @Override
    public DefaultTableModel getAllSalesReceiptDetail() {
        return salesReceiptDetailRepository.getAllSalesReceiptDetail();
    }
}
