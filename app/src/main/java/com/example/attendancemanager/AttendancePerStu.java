package com.example.attendancemanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AttendancePerStu extends AppCompatActivity {

    String[] data={"Id No 101: Student 1: 5 ","Id No 102: Student 2: 8","Id No 103: Student 3: 6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        ListView lv=(ListView) findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        lv.setAdapter(arrayAdapter);
    }
}
