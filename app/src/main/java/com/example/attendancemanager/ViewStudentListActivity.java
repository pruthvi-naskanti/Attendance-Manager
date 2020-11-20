package com.example.attendancemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.adapter.ViewStudentAdapter;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;

import java.util.List;

public class ViewStudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        RecyclerView recyclerView = findViewById(R.id.listview);
        List<Student> data = getIntent().getParcelableArrayListExtra(Constants.STUDENT_LIST);
        ViewStudentAdapter adapter = new ViewStudentAdapter(data);
        recyclerView.setAdapter(adapter);
    }


}
