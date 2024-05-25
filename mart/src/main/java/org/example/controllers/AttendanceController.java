//package org.example.controllers;
//
//import org.example.models.Attendance;
//
//import java.util.List;
//import java.util.Optional;
//
//public class AttendanceController {
//
//    private AttendanceRepository attendanceRepository;
//
//    public List<Attendance> getAllAttendances() {
//        return attendanceRepository.findAll();
//    }
//
//    public Attendance getAttendanceByID(int attendanceID) {
//        Optional<Attendance> getAttendanceByID = attendanceRepository.findById(attendanceID);
//        return getAttendanceByID.orElse(null);
//    }
//
//    public void addAttendance(Attendance attendance) {
//        // Code để thêm một bản ghi chấm công mới vào cơ sở dữ liệu
//    }
//
//    public void updateAttendance(Attendance attendance) {
//        // Code để cập nhật thông tin của một bản ghi chấm công trong cơ sở dữ liệu
//    }
//
//    public void deleteAttendance(int attendanceID) {
//        // Code để xóa một bản ghi chấm công khỏi cơ sở dữ liệu dựa trên ID
//    }
//}
