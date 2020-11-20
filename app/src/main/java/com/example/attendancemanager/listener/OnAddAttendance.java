package com.example.attendancemanager.listener;

import com.example.attendancemanager.models.Student;

import java.util.Map;

public interface OnAddAttendance {
    void onPresentAttendanceClick(int position, Student student);
    void onAbsentAttendanceClick(int position, Student student);
}
