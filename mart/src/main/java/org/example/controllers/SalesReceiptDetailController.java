//package org.example.controllers;
//
//import org.example.models.SalesReceiptDetail;
//import org.example.repository.SalesReceiptDetailRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class SalesReceiptDetailController {
//
//    private SalesReceiptDetailRepository salesReceiptDetailRepository;
//
//    public List<SalesReceiptDetail> getAllSalesReceiptDetails() {
//        return salesReceiptDetailRepository.findAll();
//        // Code để lấy tất cả thông tin về chi tiết phiếu bán hàng từ cơ sở dữ liệu
//        // và trả về danh sách chi tiết phiếu bán hàng
//    }
//
//    public SalesReceiptDetail getSalesReceiptDetailByID(int detailID) {
//        Optional<SalesReceiptDetail> getSalesReceiptDetailByID = salesReceiptDetailRepository.findById(detailID);
//        return getSalesReceiptDetailByID.orElse(null);
//        // Code để lấy thông tin về chi tiết phiếu bán hàng dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng chi tiết phiếu bán hàng tương ứng
//    }
//
//    public void addSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
//        // Code để thêm một bản ghi chi tiết phiếu bán hàng mới vào cơ sở dữ liệu
//    }
//
//    public void updateSalesReceiptDetail(SalesReceiptDetail salesReceiptDetail) {
//        // Code để cập nhật thông tin của một bản ghi chi tiết phiếu bán hàng trong cơ sở dữ liệu
//    }
//
//    public void deleteSalesReceiptDetail(int detailID) {
//        // Code để xóa một bản ghi chi tiết phiếu bán hàng khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
