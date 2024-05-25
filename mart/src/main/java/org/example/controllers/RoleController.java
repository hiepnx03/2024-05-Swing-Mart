//package org.example.controllers;
//
//import org.example.models.Role;
//import org.example.repository.RoleRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class RoleController {
//
//    private RoleRepository roleRepository;
//
//    public List<Role> getAllRoles() {
//        return roleRepository.findAll();
//        // Code để lấy tất cả các quyền từ cơ sở dữ liệu
//        // và trả về danh sách các quyền
//    }
//
//    public Role getRoleByID(int roleID) {
//        Optional<Role> getRoleByID = roleRepository.findById(roleID);
//        return getRoleByID.orElse(null);
//        // Code để lấy thông tin quyền dựa trên ID từ cơ sở dữ liệu
//        // và trả về đối tượng quyền tương ứng
//    }
//
//    public void addRole(Role role) {
//        // Code để thêm một quyền mới vào cơ sở dữ liệu
//    }
//
//    public void updateRole(Role role) {
//        // Code để cập nhật thông tin của một quyền trong cơ sở dữ liệu
//    }
//
//    public void deleteRole(int roleID) {
//        // Code để xóa một quyền khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
//
