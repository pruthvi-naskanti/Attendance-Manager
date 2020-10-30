package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
                String usrname=UserName.getText().toString().trim();
                String pswd=password.getText().toString().trim();
                if(TextUtils.isEmpty(usrname)){
                    UserName.setError("Username is Required");
                    return;
                }
                if(TextUtils.isEmpty(pswd)){
                    password.setError("Password is Empty");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(usrname,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddFaculty.this,"User Successfully added",Toast.LENGTH_LONG).show();
                            Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
                            startActivity(I);
                        }else{
                            Toast.makeText(AddFaculty.this,"Error Occured"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });


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
