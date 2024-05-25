//package org.example.controllers;
//
//import org.example.models.Supplier;
//import org.example.repository.SupplierRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class SupplierController {
//
//    private SupplierRepository supplierRepository;
//
//    public List<Supplier> getAllSuppliers() {
//        return supplierRepository.findAll();
//        // Code để lấy tất cả thông tin nhà cung cấp từ cơ sở dữ liệu
//        // và trả về danh sách nhà cung cấp
//    }
//
//    public Supplier getSupplierByID(int supplierID) {
//        Optional<Supplier> getSupplierByID = supplierRepository.findById(supplierID);
//        return getSupplierByID.orElse(null);
//        // Code để lấy thông tin nhà cung cấp dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng nhà cung cấp tương ứng
//    }
//
//    public void addSupplier(Supplier supplier) {
//        // Code để thêm một bản ghi nhà cung cấp mới vào cơ sở dữ liệu
//    }
//
//    public void updateSupplier(Supplier supplier) {
//        // Code để cập nhật thông tin của một bản ghi nhà cung cấp trong cơ sở dữ liệu
//    }
//
//    public void deleteSupplier(int supplierID) {
//        // Code để xóa một bản ghi nhà cung cấp khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
