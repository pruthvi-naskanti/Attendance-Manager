package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.example.attendancemanager.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText etFirstName;
    private EditText etLastName;
    private Spinner spinnerDept;
    private Spinner spinnerYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        db = FirebaseFirestore.getInstance();

        etFirstName = findViewById(R.id.editTextFirstName);
        etLastName = findViewById(R.id.editTextLastName);
        spinnerDept = findViewById(R.id.spinnerdept);
        spinnerYear = findViewById(R.id.spinneryear);

    }

    public void submit(View v) {
        String  firstName = etFirstName.getText().toString();
        String  lastName = etLastName.getText().toString();

        if (!validateInputs(firstName,lastName)) return;
        fetchRecentStudentId(firstName,lastName,spinnerDept.getSelectedItem().toString(),spinnerYear.getSelectedItem().toString());
    }

    public void cancel(View v) {
        finish();
    }

    private boolean validateInputs(String firstName,String lastName){

        if (firstName.isEmpty()){
            Toast.makeText(this, getString(R.string.first_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (lastName.isEmpty()){
            Toast.makeText(this, getString(R.string.last_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void fetchRecentStudentId(final String firstName, final String lastName, final String dept, final String year){

        db.collection(Constants.id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            int id = Integer.parseInt(task.getResult().getDocuments().get(0).getData().get(Constants.recentStudentId).toString());
                            Map<String,Boolean> map = new HashMap<>();
                            Student student = new Student(
                                    id,
                                    new Timestamp(new Date()),
                                    firstName,
                                    lastName,
                                    year,
                                    dept,
                                    0,
                                    0,
                                    map,
                                    map,
                                    map,
                                    map,
                                    map
                            );
                            addStudent(student);
                        }else{
                            Toast.makeText(AddStudentActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void addStudent(Student student){

        db.collection(Constants.student)
                .add(student)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        incrementRecentStudentId();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddStudentActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void incrementRecentStudentId(){
        db.collection(Constants.id).document(Constants.recentStudentIndex)
                .update(Constants.recentStudentId, FieldValue.increment(1)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddStudentActivity.this, getString(R.string.student_added), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddStudentActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
