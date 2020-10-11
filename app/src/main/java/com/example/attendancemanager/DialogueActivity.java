package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class DialogueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Button B;
        B=findViewById(R.id.attendanceSubmitButton);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getApplicationContext(), AddAttendanceActivity.class);
                startActivity(I);
                //setContentView(R.layout.home_screen);
                Toast.makeText(getApplicationContext(), "Attendance Submitted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
