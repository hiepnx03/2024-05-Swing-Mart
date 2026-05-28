package org.example.controllers;

import org.example.models.Supplier;
import org.example.services.SupplierService;
import org.example.services.impl.SupplierServiceImpl;

import javax.swing.table.DefaultTableModel;

public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController() {
        this.supplierService = new SupplierServiceImpl();
    }

    public Supplier getSupplierByName(String supplierName) {
        return supplierService.getSupplierByName(supplierName);
    }

    public DefaultTableModel getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    public boolean addSupplier(Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

    public boolean deleteSupplier(int supplierID) {
        return supplierService.deleteSupplier(supplierID);
    }
}
