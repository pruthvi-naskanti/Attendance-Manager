package com.example.attendancemanager.other;

import com.example.attendancemanager.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String getTotalPresent(Student student) {
        return String.valueOf(getPresentDays(student.getAdbAttendance())
                + getPresentDays(student.getMlAttendance())
                + getPresentDays(student.getAndroidAttendance())
                + getPresentDays(student.getiOSAttendance())
                + getPresentDays(student.getJavaAttendance()));
    }

    private static int getPresentDays(Map<String, Boolean> subjectAttendanceData) {
        int count = 0;
        List<Boolean> values = new ArrayList<>(subjectAttendanceData.values());
        for (Boolean item : values) {
            if (item) {
                count++;
            }
        }
        return count;
    }

    public static Map<String, Boolean> subjectAttendanceMap(String subject, Student student) {
        switch (subject) {
            case Constants.androidAttendance:
                return student.getAndroidAttendance();
            case Constants.iOSAttendance:
                return student.getiOSAttendance();
            case Constants.javaAttendance:
                return student.getJavaAttendance();
            case Constants.mlAttendance:
                return student.getMlAttendance();
            case Constants.adbAttendance:
                return student.getAdbAttendance();

        }
        return new HashMap<>();
    }


}
