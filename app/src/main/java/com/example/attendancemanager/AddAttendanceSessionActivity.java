package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddAttendanceSessionActivity extends AppCompatActivity {
    Spinner dept;
    Spinner year;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);
        submit=findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(), AddAttendanceActivity.class);
                startActivity(I);
                setContentView(R.layout.listview_main);
            }
        });
    }






/*      public void addattendance(View v) {
          Intent I = new Intent(getApplicationContext(), AddAttendanceActivity.class);
          startActivity(I);
          setContentView(R.layout.listview_main);

      }*/


}
