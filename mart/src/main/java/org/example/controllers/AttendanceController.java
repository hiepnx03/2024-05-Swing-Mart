package org.example.controllers;

import org.example.services.AttendanceService;
import org.example.services.impl.AttendanceServiceImpl;

import java.util.Date;

public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController() {
        this.attendanceService = new AttendanceServiceImpl();
    }

    public boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift) {
        return attendanceService.markAttendance(employeeID, date, hoursWorked, shift);
    }
}
