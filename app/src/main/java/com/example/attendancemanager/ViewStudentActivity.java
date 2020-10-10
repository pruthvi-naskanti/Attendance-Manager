package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);

    }

//    public void submitbtn(View v) {
//        Intent I = new Intent(getApplicationContext(), ViewAttendanceList.class);
//        startActivity(I);
//        setContentView(R.layout.view_attendence_list);
//        Toast.makeText(getApplicationContext(), "List of students in Class", Toast.LENGTH_LONG).show();
//    }

}
