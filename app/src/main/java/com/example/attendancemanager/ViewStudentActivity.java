package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    private Button B;
    private FirebaseFirestore db;
    private ArrayList<Student> studentList = new ArrayList<>();
    private Spinner spinnerDept;
    private Spinner spinnerYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);

        db = FirebaseFirestore.getInstance();

        spinnerDept = findViewById(R.id.spinnerbranchView);
        spinnerYear = findViewById(R.id.spinneryearView);

        B=findViewById(R.id.submitButton);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilteredStudent(spinnerDept.getSelectedItem().toString(),spinnerYear.getSelectedItem().toString());
            }
        });
    }

    private void getFilteredStudent(String dept,String year){

        db.collection(Constants.student)
                .whereEqualTo(Constants.dept,dept)
                .whereEqualTo(Constants.year,year)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot item : task.getResult()){
                                studentList.add(item.toObject(Student.class));
                            }
                            Intent i = new Intent(getApplicationContext(), ViewStudentListActivity.class);
                            i.putParcelableArrayListExtra(Constants.STUDENT_LIST,studentList);
                            startActivity(i);
                        }else{
                            Toast.makeText(ViewStudentActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}
