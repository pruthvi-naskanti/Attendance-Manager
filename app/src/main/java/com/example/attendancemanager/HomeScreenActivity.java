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
        Intent vintent=new Intent(getApplicationContext(), AttendancePerStu.class);
        startActivity(vintent);
        setContentView(R.layout.view_attendance_list_per_student);
    }

    public void AddFaculty(View v) {
        Intent I = new Intent(getApplicationContext(), AddFaculty.class);
        startActivity(I);
        setContentView(R.layout.add_faculty);
    }
    }



