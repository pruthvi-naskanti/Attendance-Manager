package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancemanager.models.Faculty;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AddFaculty extends AppCompatActivity {

    private EditText etFirstName, etLastName, etUsername, etPassword, etAddress, etPhone;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_faculty);

        db = FirebaseFirestore.getInstance();

        etFirstName = findViewById(R.id.editTextFirstName);
        etLastName = findViewById(R.id.editTextLastName);
        etUsername = findViewById(R.id.editTextUserName);
        etPassword = findViewById(R.id.editTextPassword);
        etAddress = findViewById(R.id.editTextaddr);
        etPhone = findViewById(R.id.editTextPhone);
    }


    public void cancel(View v) {
        Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(I);
        //setContentView(R.layout.home_screen);
    }

    public void addFacultyClick(View view) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String username = etUsername.getText().toString();
        String address = etAddress.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();

        if (!validateInputs(firstName, lastName, username, address, password, phone)) return;

        Faculty faculty = new Faculty(firstName, lastName, username, address, password, phone);

        verifyUsernameUnique(faculty);
    }

    private void verifyUsernameUnique(final Faculty faculty) {
        db.collection("faculty")
                .whereEqualTo(Constants.username,faculty.getUsername())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult().getDocuments().size()>0){
                                Toast.makeText(AddFaculty.this, getString(R.string.username_already_exist), Toast.LENGTH_SHORT).show();
                            }else{
                                addFacultyToDatabase(faculty);
                            }
                        }else{
                            Toast.makeText(AddFaculty.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void addFacultyToDatabase(Faculty faculty) {
        db.collection("faculty")
                .add(faculty)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddFaculty.this, "Faculty Added!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddFaculty.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean validateInputs(String firstName, String lastName, String username, String address, String password, String phone) {

        if (firstName.isEmpty()) {
            Toast.makeText(this, "First Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (lastName.isEmpty()) {
            Toast.makeText(this, "Last Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Phone cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (address.isEmpty()) {
            Toast.makeText(this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 4 || password.length() >= 20) {
            Toast.makeText(this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

}

