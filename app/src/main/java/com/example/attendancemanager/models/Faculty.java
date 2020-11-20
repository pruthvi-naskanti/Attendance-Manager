package com.example.attendancemanager.models;

public class Faculty {

    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String password;
    private String phone;

    public Faculty(){}

    public Faculty(String firstName, String lastName, String username, String address, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
