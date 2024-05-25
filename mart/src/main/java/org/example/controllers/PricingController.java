//package org.example.controllers;
//
//import org.example.models.Pricing;
//import org.example.repository.PricingRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class PricingController {
//
//    private PricingRepository pricingRepository;
//
//    public List<Pricing> getAllPricings() {
//        return pricingRepository.findAll();
//        // Code để lấy tất cả thông tin về giá bán từ cơ sở dữ liệu
//        // và trả về danh sách giá bán
//    }
//
//    public Pricing getPricingByID(int priceID) {
//        Optional<Pricing> getPricingByID = pricingRepository.findById(priceID);
//        return getPricingByID.orElse(null);
//        // Code để lấy thông tin về giá bán dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng giá bán tương ứng
//    }
//
//    public void addPricing(Pricing pricing) {
//        // Code để thêm một bản ghi giá bán mới vào cơ sở dữ liệu
//    }
//
//    public void updatePricing(Pricing pricing) {
//        // Code để cập nhật thông tin của một bản ghi giá bán trong cơ sở dữ liệu
//    }
//
//    public void deletePricing(int priceID) {
//        // Code để xóa một bản ghi giá bán khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
