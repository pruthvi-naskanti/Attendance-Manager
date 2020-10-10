package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

    }


    public void addstu(View v) {
        Intent I = new Intent(getApplicationContext(), AddStudentActivity.class);
        startActivity(I);
        setContentView(R.layout.add_student);

    }
    public void viewstu(View v){
        Intent vintent=new Intent(getApplicationContext(), ViewStudentActivity.class);
        startActivity(vintent);
        setContentView(R.layout.view_student);
    }

    public void removestu(View v){
        Intent vintent=new Intent(getApplicationContext(), RemoveStudentActivity.class);
        startActivity(vintent);
        setContentView(R.layout.remove_student);
    }

    public void attendanceperstudent(View v){
        Intent I=new Intent(getApplicationContext(), AttendancePerStu.class);
        startActivity(I);
        setContentView(R.layout.listview_main);
    }

    public void AddFaculty(View v) {
        Intent I = new Intent(getApplicationContext(), AddFaculty.class);
        startActivity(I);
        setContentView(R.layout.add_faculty);
    }
    public void Viewfaculty(View v) {
        Intent I = new Intent(getApplicationContext(), ViewFacultyActivity.class);
        startActivity(I);
        setContentView(R.layout.view_faculty_list);
    }
    public void Removefaculty(View v) {
        Intent I = new Intent(getApplicationContext(), RemoveFacultyActivity.class);
        startActivity(I);
        setContentView(R.layout.remove_faculty);
    }

    public void logout(View v) {
        Intent I = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(I);
        setContentView(R.layout.activity_main);
    }


    }



