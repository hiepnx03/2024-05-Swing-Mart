package org.example.services.impl;

import org.example.repositories.AttendanceRepository;
import org.example.repositories.impl.AttendanceRepositoryImpl;
import org.example.services.AttendanceService;

import java.util.Date;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository = new AttendanceRepositoryImpl();

    @Override
    public boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift) {
        return attendanceRepository.markAttendance(employeeID, date, hoursWorked, shift);
    }
}
