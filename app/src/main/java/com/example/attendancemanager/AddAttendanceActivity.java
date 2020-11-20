package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.example.attendancemanager.adapter.AddAttendanceAdapter;
import com.example.attendancemanager.listener.OnAddAttendance;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.example.attendancemanager.other.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.attendancemanager.other.Utils.subjectAttendanceMap;

public class AddAttendanceActivity extends AppCompatActivity implements OnAddAttendance {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private AddAttendanceAdapter adapter ;
    private String year,date,subject,dept;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<QueryDocumentSnapshot> queryDocumentSnapshotsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        year = intent.getStringExtra(Constants.year);
        dept = intent.getStringExtra(Constants.dept);
        date = intent.getStringExtra(Constants.DATE);
        subject = intent.getStringExtra(Constants.SUBJECT);

        recyclerView = findViewById(R.id.listview);
        fetchStudent();
    }

    @Override
    public void onPresentAttendanceClick(int position, Student student) {
        Map<String,Boolean> subjectAttendance= subjectAttendanceMap(subject,student);
        if (!subjectAttendance.containsKey(date)){
            subjectAttendance.put(date,true);
            updateAttendance(queryDocumentSnapshotsList.get(position).getId(),subjectAttendance);
            adapter.notifyDataSetChanged();
        }else{
            boolean isPresent = subjectAttendance.get(date);
            if (!isPresent){
                subjectAttendance.put(date,true);
                updateAttendance(queryDocumentSnapshotsList.get(position).getId(),subjectAttendance);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onAbsentAttendanceClick(int position, Student student) {
        Map<String,Boolean> subjectAttendance= subjectAttendanceMap(subject,student);
        if (!subjectAttendance.containsKey(date)){
            subjectAttendance.put(date,false);
            updateAttendance(queryDocumentSnapshotsList.get(position).getId(),subjectAttendance);
            adapter.notifyDataSetChanged();
        }else{
            boolean isPresent = subjectAttendance.get(date);
            if (isPresent){
                subjectAttendance.put(date,false);
                updateAttendance(queryDocumentSnapshotsList.get(position).getId(),subjectAttendance);
                adapter.notifyDataSetChanged();
            }
        }
    }



    private void fetchStudent(){

        db.collection(Constants.student)
                .whereEqualTo(Constants.year,year)
                .whereEqualTo(Constants.dept,dept)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot item : task.getResult()){
                                queryDocumentSnapshotsList.add(item);
                                studentList.add(item.toObject(Student.class));
                            }
                            adapter = new AddAttendanceAdapter(studentList,subject,AddAttendanceActivity.this);
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(AddAttendanceActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void updateAttendance(String studentDocumentID, Map<String, Boolean> attendanceMap){

        db.collection(Constants.student)
                .document(studentDocumentID)
                .update(subject,attendanceMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddAttendanceActivity.this, "Attnedance Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddAttendanceActivity.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
