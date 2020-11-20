package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.adapter.ViewTotalAttendanceAdapter;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.attendancemanager.other.Utils.subjectAttendanceMap;

public class ViewTotalAttendanceStudentActivityFacultyModule extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private String year,subject,dept;
    private ArrayList<Object> data = new ArrayList<>();
    private Map<String, List<Map<String,Object>>> mapData = new HashMap<>();
    private ArrayList<String> classDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_attendance_student);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView2);

        Intent intent = getIntent();
        year = intent.getStringExtra(Constants.year);
        dept = intent.getStringExtra(Constants.dept);
        subject = intent.getStringExtra(Constants.SUBJECT);

        fetchStudents();
    }

    private void fetchStudents(){

        db.collection(Constants.student)
                .whereEqualTo(Constants.year,year)
                .whereEqualTo(Constants.dept,dept)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            ArrayList<Student> filteredStudentList = new ArrayList<>();
                            setTotalClassAttendanceDates(task.getResult());
                            for (QueryDocumentSnapshot item : task.getResult()){
                                Student student = item.toObject(Student.class);
                                Map<String,Boolean> studentSubjectAttendance = subjectAttendanceMap(subject,student);
                                addPresentAttendanceStudentToGroupedDateMap(student,studentSubjectAttendance);
                            }
                            makeHeterogeneousList();
                            ViewTotalAttendanceAdapter adapter = new ViewTotalAttendanceAdapter(data);
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(ViewTotalAttendanceStudentActivityFacultyModule.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setTotalClassAttendanceDates(QuerySnapshot querySnapshot){
        for (QueryDocumentSnapshot item : querySnapshot){
            Map<String,Boolean> subjectAttendanceMap =  subjectAttendanceMap(subject,item.toObject(Student.class));
            for (String date : subjectAttendanceMap.keySet()){
                if (!classDates.contains(date)){
                    classDates.add(date);
                }
            }
        }
    }

    private void addPresentAttendanceStudentToGroupedDateMap(Student student,Map<String,Boolean> studentSubjectAttendance){
        for (String date : classDates){

            HashMap<String,Object> studentMap = new HashMap<>();
            studentMap.put(Constants.student,student);

            ArrayList<Map<String,Object>> groupedStudents;
            if (mapData.containsKey(date)){
                groupedStudents = (ArrayList<Map<String,Object>>) mapData.get(date);
            }else{
                groupedStudents = new ArrayList<>();
            }

            if (studentSubjectAttendance.containsKey(date)){
                boolean isPresent = studentSubjectAttendance.get(date);
                studentMap.put(Constants.AVAILABILITY, isPresent);
            }else{
                studentMap.put(Constants.AVAILABILITY,false);
            }
            groupedStudents.add(studentMap);
            mapData.put(date,groupedStudents);
        }
    }

    private void makeHeterogeneousList(){
        for (String date : mapData.keySet()){
            data.add(date);
            data.addAll(mapData.get(date));
        }
    }


}
