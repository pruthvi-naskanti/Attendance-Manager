package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddAttendanceSessionActivity extends AppCompatActivity {
    Spinner dept;
    Spinner year;
    Button submit;
    private EditText dateEditText;
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);
        submit=findViewById(R.id.buttonsubmit);

        date = (ImageButton) findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);

    }

    public void view_attendance(View v){
        Intent I = new Intent(getApplicationContext(), ViewAttendanceByFacultyActivity.class);
        startActivity(I);
        //setContentView(R.layout.listview_main);
    }


      public void addattendance(View v) {
          Intent I = new Intent(getApplicationContext(), AddAttendanceActivity.class);
          startActivity(I);
          //setContentView(R.layout.listview_main);

      }

    public void view_total_attendance(View v){
        Intent I = new Intent(getApplicationContext(), ViewAttendanceByFacultyActivity.class);
        startActivity(I);
        //setContentView(R.layout.listview_main);
    }


}
