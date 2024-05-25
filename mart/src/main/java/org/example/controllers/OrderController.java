//package org.example.controllers;
//
//import org.example.models.Order;
//import org.example.repository.OrderRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class OrderController {
//
//    private OrderRepository orderRepository;
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//        // Code để lấy tất cả thông tin đơn hàng từ cơ sở dữ liệu
//        // và trả về danh sách đơn hàng
//    }
//
//    public Order getOrderByID(int orderID) {
//        Optional<Order> getOrderByID = orderRepository.findById(orderID);
//        return getOrderByID.orElse(null);
//        // Code để lấy thông tin đơn hàng dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng đơn hàng tương ứng
//    }
//
//    public void addOrder(Order order) {
//        // Code để thêm một bản ghi đơn hàng mới vào cơ sở dữ liệu
//    }
//
//    public void updateOrder(Order order) {
//        // Code để cập nhật thông tin của một bản ghi đơn hàng trong cơ sở dữ liệu
//    }
//
//    public void deleteOrder(int orderID) {
//        // Code để xóa một bản ghi đơn hàng khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
