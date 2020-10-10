package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

    }

    public void submit(View v) {
        Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(I);
        setContentView(R.layout.home_screen);
        Toast.makeText(getApplicationContext(), "Student Successfully Added", Toast.LENGTH_LONG).show();
    }
}
