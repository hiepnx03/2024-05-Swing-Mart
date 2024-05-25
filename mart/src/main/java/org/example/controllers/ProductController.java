//package org.example.controllers;
//
//import org.example.models.Product;
//import org.example.repository.ProductRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class ProductController {
//
//    private ProductRepository productRepository;
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//        // Code để lấy tất cả thông tin sản phẩm từ cơ sở dữ liệu
//        // và trả về danh sách sản phẩm
//    }
//
//    public Product getProductByID(int productID) {
//        Optional<Product> getProductByID = productRepository.findById(productID);
//        return getProductByID.orElse(null);
//        // Code để lấy thông tin sản phẩm dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng sản phẩm tương ứng
//    }
//
//    public void addProduct(Product product) {
//        // Code để thêm một bản ghi sản phẩm mới vào cơ sở dữ liệu
//    }
//
//    public void updateProduct(Product product) {
//        // Code để cập nhật thông tin của một bản ghi sản phẩm trong cơ sở dữ liệu
//    }
//
//    public void deleteProduct(int productID) {
//        // Code để xóa một bản ghi sản phẩm khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
