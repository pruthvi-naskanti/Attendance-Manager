package com.example.attendancemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class RemoveFacultyActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_faculty);

        db = FirebaseFirestore.getInstance();
        editText = findViewById(R.id.facfnameET);
        Button remove = findViewById(R.id.facremovebtn);
        Button cancel = findViewById(R.id.faccbtn);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText.getText().toString();
                if (!validateInput(username)) return;
                checkFacultyExist(username);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean validateInput(String username) {
        if (username.isEmpty()) {
            Toast.makeText(this, getString(R.string.username_cannot_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkFacultyExist(String username) {

        db.collection(Constants.faculty)
                .whereEqualTo(Constants.username, username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 1) {
                                deleteFaculty(task.getResult().getDocuments().get(0).getId());
                                return;
                            }else{
                                Toast.makeText(RemoveFacultyActivity.this,"Username not exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(RemoveFacultyActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteFaculty(String documentId) {

        db.collection(Constants.faculty).document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RemoveFacultyActivity.this, getString(R.string.faculty_deleted), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RemoveFacultyActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
