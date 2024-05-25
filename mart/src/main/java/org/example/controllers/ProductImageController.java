//package org.example.controllers;
//
//import org.example.models.ProductImage;
//import org.example.repository.ProductImageRepository;
//import org.example.repository.ProductRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class ProductImageController {
//
//    private ProductImageRepository productImageRepository;
//
//    public List<ProductImage> getAllProductImages() {
//        return productImageRepository.findAll();
//        // Code để lấy tất cả thông tin về hình ảnh sản phẩm từ cơ sở dữ liệu
//        // và trả về danh sách hình ảnh sản phẩm
//    }
//
//    public ProductImage getProductImageByID(int imageID) {
//        Optional<ProductImage> getProductImageByID = productImageRepository.findById(imageID);
//        return getProductImageByID.orElse(null);
//        // Code để lấy thông tin về hình ảnh sản phẩm dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng hình ảnh sản phẩm tương ứng
//    }
//
//    public void addProductImage(ProductImage productImage) {
//        // Code để thêm một bản ghi hình ảnh sản phẩm mới vào cơ sở dữ liệu
//    }
//
//    public void updateProductImage(ProductImage productImage) {
//        // Code để cập nhật thông tin của một bản ghi hình ảnh sản phẩm trong cơ sở dữ liệu
//    }
//
//    public void deleteProductImage(int imageID) {
//        // Code để xóa một bản ghi hình ảnh sản phẩm khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
