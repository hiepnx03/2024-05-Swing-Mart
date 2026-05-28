package org.example.repositories;

import java.util.Date;

public interface AttendanceRepository {
    boolean markAttendance(int employeeID, Date date, int hoursWorked, String shift);
}
