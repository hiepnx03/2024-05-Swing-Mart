package org.example.services.impl;

import org.example.models.Supplier;
import org.example.repositories.SupplierRepository;
import org.example.repositories.impl.SupplierRepositoryImpl;
import org.example.services.SupplierService;

import javax.swing.table.DefaultTableModel;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public Supplier getSupplierByName(String supplierName) {
        return supplierRepository.getSupplierByName(supplierName);
    }

    @Override
    public DefaultTableModel getAllSuppliers() {
        return supplierRepository.getAllSuppliers();
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        return supplierRepository.addSupplier(supplier);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierRepository.updateSupplier(supplier);
    }

    @Override
    public boolean deleteSupplier(int supplierID) {
        return supplierRepository.deleteSupplier(supplierID);
    }
}
