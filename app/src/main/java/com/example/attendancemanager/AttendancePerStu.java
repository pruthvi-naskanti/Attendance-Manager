package com.example.attendancemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.adapter.AttendancePerStuAdapter;
import com.example.attendancemanager.adapter.AttendancePerStudentAdapter;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AttendancePerStu extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.listview);
        fetchStudentAttendance();
    }

    private void fetchStudentAttendance(){

        db.collection(Constants.student)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            ArrayList<Student> data = new ArrayList<>();
                            for (QueryDocumentSnapshot item : task.getResult()){
                                data.add(item.toObject(Student.class));
                            }
                            AttendancePerStuAdapter adapter = new AttendancePerStuAdapter(data);
                            recyclerView.setAdapter(adapter);
                        }else {
                            Toast.makeText(AttendancePerStu.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
