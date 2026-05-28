package org.example.services;

import java.util.Date;

public interface AttendanceService {
    boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift);
}
