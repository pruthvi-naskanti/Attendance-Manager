package com.example.attendancemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;

import java.util.List;
import java.util.Map;

public class Student implements Parcelable {

    private int id;
    private Timestamp createdTime;
    private String firstName;
    private String lastName;
    private String year;
    private String dept;
    private int totalPresent;
    private int totalAbsent;
    private Map<String,Boolean> androidAttendance;
    private Map<String,Boolean> javaAttendance;
    private Map<String,Boolean> iOSAttendance;
    private Map<String,Boolean> mlAttendance;
    private Map<String,Boolean> adbAttendance;

    public Student(){ }

    public Student(int id, Timestamp createdTime, String firstName, String lastName, String year, String dept, int totalPresent, int totalAbsent, Map<String, Boolean> androidAttendance, Map<String, Boolean> javaAttendance, Map<String, Boolean> iOSAttendance, Map<String, Boolean> mlAttendance, Map<String, Boolean> adbAttendance) {
        this.id = id;
        this.createdTime = createdTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.dept = dept;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.androidAttendance = androidAttendance;
        this.javaAttendance = javaAttendance;
        this.iOSAttendance = iOSAttendance;
        this.mlAttendance = mlAttendance;
        this.adbAttendance = adbAttendance;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        createdTime = in.readParcelable(Timestamp.class.getClassLoader());
        firstName = in.readString();
        lastName = in.readString();
        year = in.readString();
        dept = in.readString();
        totalPresent = in.readInt();
        totalAbsent = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(createdTime, flags);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(year);
        dest.writeString(dept);
        dest.writeInt(totalPresent);
        dest.writeInt(totalAbsent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getYear() {
        return year;
    }

    public String getDept() {
        return dept;
    }

    public int getTotalPresent() {
        return totalPresent;
    }

    public int getTotalAbsent() {
        return totalAbsent;
    }

    public Map<String, Boolean> getAndroidAttendance() {
        return androidAttendance;
    }

    public Map<String, Boolean> getJavaAttendance() {
        return javaAttendance;
    }

    public Map<String, Boolean> getiOSAttendance() {
        return iOSAttendance;
    }

    public Map<String, Boolean> getMlAttendance() {
        return mlAttendance;
    }

    public Map<String, Boolean> getAdbAttendance() {
        return adbAttendance;
    }

}
