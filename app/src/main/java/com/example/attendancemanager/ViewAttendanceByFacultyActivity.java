package com.example.attendancemanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewAttendanceByFacultyActivity extends Activity {
    String[] data={"Id No 101: Student 1: Absent ","Id No 102: Student 2: Present","Id No 103: Student 3: Absent"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        ListView lv=(ListView) findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        lv.setAdapter(arrayAdapter);
    }
}
