package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.other.SharedPref;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

    }


    public void addstu(View v) {
        Intent I = new Intent(getApplicationContext(), AddStudentActivity.class);
        startActivity(I);

    }

    public void viewstu(View v) {
        Intent vintent = new Intent(getApplicationContext(), ViewStudentActivity.class);
        startActivity(vintent);
    }

    public void removestu(View v) {
        Intent vintent = new Intent(getApplicationContext(), RemoveStudentActivity.class);
        startActivity(vintent);
    }

    public void attendanceperstudent(View v) {
        Intent I = new Intent(getApplicationContext(), AttendancePerStu.class);
        startActivity(I);
    }

    public void AddFaculty(View v) {
        Intent I = new Intent(getApplicationContext(), AddFaculty.class);
        startActivity(I);
    }

    public void Viewfaculty(View v) {
        Intent I = new Intent(getApplicationContext(), ViewFacultyActivity.class);
        startActivity(I);
    }

    public void Removefaculty(View v) {
        Intent I = new Intent(getApplicationContext(), RemoveFacultyActivity.class);
        startActivity(I);
    }

    public void logout(View v) {
        SharedPref sharedPref = SharedPref.getInstance();
        sharedPref.clearSharedPref(this);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}



