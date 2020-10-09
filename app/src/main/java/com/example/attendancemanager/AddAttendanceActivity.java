package com.example.attendancemanager;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddAttendanceActivity extends AppCompatActivity {

    Spinner dept;
    Spinner year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);

    }

}
