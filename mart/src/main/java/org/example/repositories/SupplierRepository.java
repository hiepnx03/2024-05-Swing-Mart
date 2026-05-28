package org.example.repositories;

import org.example.models.Supplier;
import javax.swing.table.DefaultTableModel;

public interface SupplierRepository {
    Supplier getSupplierByName(String supplierName);
    DefaultTableModel getAllSuppliers();
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(int supplierID);
}
