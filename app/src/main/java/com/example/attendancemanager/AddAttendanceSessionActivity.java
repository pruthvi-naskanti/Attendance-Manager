package com.example.attendancemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.other.Constants;
import com.example.attendancemanager.other.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddAttendanceSessionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner dept;
    private Spinner year;
    private Spinner subject;
    private TextView tvDate;
    private int mDay, mMonth, mYear;
    private ImageButton iBtnDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);

        dept = findViewById(R.id.spinner1);
        year = findViewById(R.id.spinneryear);
        subject = findViewById(R.id.spinnerSubject);
        tvDate = findViewById(R.id.textView);
        iBtnDatePicker = findViewById(R.id.DateImageButton);

        iBtnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    public void     view_attendance(View v) {
        Intent i = new Intent(getApplicationContext(), ViewAttendanceByFacultyActivity.class);
        i.putExtra(Constants.dept, dept.getSelectedItem().toString());
        i.putExtra(Constants.year, year.getSelectedItem().toString());
        i.putExtra(Constants.SUBJECT, subjectKey(subject.getSelectedItem().toString()));
        i.putExtra(Constants.DATE,getDate(Calendar.getInstance().getTime()));

        startActivity(i);
    }


    public void addAttendance(View v) {
        String date = tvDate.getText().toString();
        if (tvDate.getText().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.select_date_first), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(getApplicationContext(), AddAttendanceActivity.class);
        i.putExtra(Constants.dept, dept.getSelectedItem().toString());
        i.putExtra(Constants.year, year.getSelectedItem().toString());
        i.putExtra(Constants.SUBJECT, subjectKey(subject.getSelectedItem().toString()));
        i.putExtra(Constants.DATE,date);

        startActivity(i);
    }

    private String subjectKey(String subject){
        switch (subject){
            case "Android":
                return Constants.androidAttendance;
            case "IOS":
                return Constants.iOSAttendance;
            case "Java":
                return Constants.javaAttendance;
            case "ML":
                return Constants.mlAttendance;
            case "ADB":
                return Constants.adbAttendance;

        }
        return "";
    }

    public void view_total_attendance(View v) {
        Intent i = new Intent(AddAttendanceSessionActivity.this, ViewTotalAttendanceStudentActivityFacultyModule.class);
        i.putExtra(Constants.dept, dept.getSelectedItem().toString());
        i.putExtra(Constants.year, year.getSelectedItem().toString());
        i.putExtra(Constants.SUBJECT, subjectKey(subject.getSelectedItem().toString()));
        startActivity(i);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    public void onLogoutClick(View view) {
        SharedPref sharedPref = SharedPref.getInstance();
        sharedPref.clearSharedPref(this);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mYear = i;
        mMonth = i1;
        mDay = i2;

        Calendar currDate = Calendar.getInstance();
        currDate.set(mYear, mMonth, mDay);
        tvDate.setText(getDate(currDate.getTime()));

    }

    private String getDate(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
        return spf.format(date);
    }

}
