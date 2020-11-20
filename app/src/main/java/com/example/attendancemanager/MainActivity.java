package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.attendancemanager.other.Constants;
import com.example.attendancemanager.other.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private FirebaseFirestore db;
    private EditText etUsername,etPassword;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref= SharedPref.getInstance();
        String loginType = sharedPref.isLoggedIn(this);
        if (!loginType.isEmpty()){
            if (loginType.equals(Constants.ADMIN)){
                startActivity(new Intent(this,HomeScreenActivity.class));
            }else{
                startActivity(new Intent(this,AddAttendanceSessionActivity.class));
            }
            finish();
        }

        db = FirebaseFirestore.getInstance();

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        spinner = findViewById(R.id.spinnerloginas);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void loginclick(View v) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (!validateInput(username,password)) return;

        authenticateUser(username,password);
    }

    private boolean validateInput(String username, String password) {

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

    private void authenticateUser(String username, String password){

        String userrole = spinner.getSelectedItem().toString();
        if (userrole.equals("Admin")) {
            authenticateAdmin(username, password);
        } else {
            authenticateFaculty(username, password);
        }
    }

    private void authenticateFaculty(String username, String password) {
        db.collection("faculty")
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                if (task.getResult().size() == 1) {
                                    sharedPref.setLoginType(MainActivity.this,Constants.FACULTY);
                                    Intent I = new Intent(getApplicationContext(), AddAttendanceSessionActivity.class);
                                    startActivity(I);
                                    finish();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(MainActivity.this, "Faculty not exist", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void authenticateAdmin(String username, String password){
        db.collection("admins")
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                if (task.getResult().size() == 1) {
                                    sharedPref.setLoginType(MainActivity.this,Constants.ADMIN);
                                    Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
                                    startActivity(I);
                                    finish();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(MainActivity.this, "Admin not exist", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}