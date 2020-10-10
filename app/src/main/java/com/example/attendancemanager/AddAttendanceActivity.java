package com.example.attendancemanager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddAttendanceActivity extends AppCompatActivity {
    private ListView listView;
    String status;
    Button attendanceSubmit;
    String[] studentslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);


    }

    public void listview(View v) {
        listView = findViewById(R.id.listview);
        studentslist=new String[]{
                "Student ABC",
                "Student XYZ"
        };
        final Dialog dialog = new Dialog(AddAttendanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.test_layout);
        RadioGroup radioGroup;
        RadioButton present;
        RadioButton absent;
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        present = (RadioButton) dialog.findViewById(R.id.PresentradioButton);
        absent = (RadioButton) dialog.findViewById(R.id.AbsentradioButton);
        final List<String> student_list = new ArrayList<String>(Arrays.asList(studentslist));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, student_list);
        listView.setAdapter(arrayAdapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.PresentradioButton) {

                    status = "Present";
                } else if (checkedId == R.id.AbsentradioButton) {

                    status = "Absent";
                } else {
                }
            }


        });
        attendanceSubmit = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
        attendanceSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();

            }

    });
        dialog.setCancelable(true);
        dialog.show();
    }
}
