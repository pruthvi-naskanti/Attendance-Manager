package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AddFaculty extends AppCompatActivity {

    Button B;
    FirebaseAuth fAuth;
    EditText firstname;
    EditText lastname;
    EditText Mobilenumber;
    EditText Address;
    EditText UserName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_faculty);
        B=findViewById(R.id.RegisterButton);
        fAuth = FirebaseAuth.getInstance();

        firstname=findViewById(R.id.editTextFirstName);
        lastname=findViewById(R.id.editTextLastName);
        Mobilenumber=findViewById(R.id.editTextPhone);
        Address=findViewById(R.id.editTextaddr);
        UserName=findViewById(R.id.editTextUserName);
        password=findViewById(R.id.editTextPassword);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(I);
                //setContentView(R.layout.home_screen);
            }
        });
    }


    public void cancel(View v) {
        Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(I);
        //setContentView(R.layout.home_screen);
    }
}
