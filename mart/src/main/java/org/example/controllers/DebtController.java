//package org.example.controllers;
//
//import org.example.models.Debt;
//import org.example.repository.DebtRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class DebtController {
//
//    private DebtRepository debtRepository;
//
//    public List<Debt> getAllDebts() {
//        return debtRepository.findAll();
//        // Code để lấy tất cả thông tin công nợ từ cơ sở dữ liệu
//        // và trả về danh sách công nợ
//    }
//
//    public Debt getDebtByID(int debtID) {
//        Optional<Debt> getDebtByID = debtRepository.findById(debtID);
//        return getDebtByID.orElse(null);
//        // Code để lấy thông tin công nợ dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng công nợ tương ứng
//    }
//
//    public void addDebt(Debt debt) {
//        // Code để thêm một bản ghi công nợ mới vào cơ sở dữ liệu
//    }
//
//    public void updateDebt(Debt debt) {
//        // Code để cập nhật thông tin của một bản ghi công nợ trong cơ sở dữ liệu
//    }
//
//    public void deleteDebt(int debtID) {
//        // Code để xóa một bản ghi công nợ khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
//
