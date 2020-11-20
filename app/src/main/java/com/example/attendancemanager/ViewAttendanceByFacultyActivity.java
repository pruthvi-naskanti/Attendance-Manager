package com.example.attendancemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.adapter.AddAttendanceAdapter;
import com.example.attendancemanager.adapter.AttendancePerStudentAdapter;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.example.attendancemanager.other.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAttendanceByFacultyActivity extends Activity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private AttendancePerStudentAdapter adapter;
    private String year, date, subject, dept;
    private final ArrayList<String> totalClassDates = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todays_attendance);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        year = intent.getStringExtra(Constants.year);
        dept = intent.getStringExtra(Constants.dept);
        date = intent.getStringExtra(Constants.DATE);
        subject = intent.getStringExtra(Constants.SUBJECT);

        recyclerView = findViewById(R.id.recyclerView);
        fetchStudents();
    }

    private void fetchStudents() {

        db.collection(Constants.student)
                .whereEqualTo(Constants.year, year)
                .whereEqualTo(Constants.dept, dept)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ;
                            ArrayList<Map<String,Object>> filteredStudentList = new ArrayList<>();

                            for (QueryDocumentSnapshot item : task.getResult()) {
                                Student student = item.toObject(Student.class);
                                Map<String, Boolean> map = Utils.subjectAttendanceMap(subject, student);
                                HashMap<String,Object> studentAvailability = new HashMap<>();
                                studentAvailability.put(Constants.student,student);

                                if (map.containsKey(date)){
                                    studentAvailability.put(Constants.AVAILABILITY, map.get(date));
                                }else{
                                    studentAvailability.put(Constants.AVAILABILITY,false);
                                }

                                filteredStudentList.add(studentAvailability);
                            }

                            adapter = new AttendancePerStudentAdapter(filteredStudentList);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(ViewAttendanceByFacultyActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
