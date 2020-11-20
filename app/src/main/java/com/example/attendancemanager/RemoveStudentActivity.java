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

public class RemoveStudentActivity extends AppCompatActivity {

    private Button REMOVE;
    private Button CANCEL;
    private EditText etStudentID;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();

        setContentView(R.layout.remove_student);
        REMOVE=findViewById(R.id.removebtn);
        CANCEL=findViewById(R.id.cbtn);
        etStudentID = findViewById(R.id.fnameET);

        REMOVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentID = etStudentID.getText().toString();
                if (!validateInput(studentID)) return;
                fetchStudentDocumentID(studentID);
            }
        });

        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    private void fetchStudentDocumentID(String studentId){
        db.collection(Constants.student)
                .whereEqualTo(Constants.id,Integer.parseInt(studentId))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult().size()==1){
                                deleteStudentDocument(task.getResult().getDocuments().get(0).getId());
                            }else {
                                Toast.makeText(RemoveStudentActivity.this, getString(R.string.student_not_exist), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RemoveStudentActivity.this,getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void deleteStudentDocument(String documentID){
        db.collection(Constants.student).document(documentID).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RemoveStudentActivity.this, getString(R.string.student_deleted), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RemoveStudentActivity.this,getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput(String id) {
        if (id.isEmpty()){
            Toast.makeText(this, getString(R.string.student_id_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
