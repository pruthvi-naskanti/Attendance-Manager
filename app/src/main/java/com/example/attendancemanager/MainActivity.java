package com.example.attendancemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button login;
    Spinner spinner;
    String userrole;
    FirebaseAuth fAuth;
    EditText UserName;
    EditText pswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.loginBtn);
        spinner=findViewById(R.id.spinnerloginas);
        spinner.setOnItemSelectedListener(this);
        fAuth=FirebaseAuth.getInstance();
        UserName=findViewById(R.id.username);
        pswd=findViewById(R.id.password);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void loginclick(View v) {
        userrole =spinner.getSelectedItem().toString();
        String pass_word = pswd.getText().toString().trim();
        if(userrole.equals("Admin")) {
            String user_name = UserName.getText().toString().trim();
            if (TextUtils.isEmpty(user_name))
            {
                UserName.setError("Invalid User Name");
            }
            else if(TextUtils.isEmpty(pass_word))
            {
                pswd.setError("enter password");
            }
            else
            {
                if(user_name.equals("admin") & pass_word.equals("admin")){
                    Intent I = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    startActivity(I);
                    Toast.makeText(getApplicationContext(), "Admin Login successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Admin Login failed", Toast.LENGTH_SHORT).show();
                }
            }
            //setContentView(R.layout.home_screen);
        }
        else{
            String usrname=UserName.getText().toString().trim();
            String pwd=pswd.getText().toString().trim();
            if(TextUtils.isEmpty(usrname)){
                UserName.setError("Username is Required");
                return;
            }
            if(TextUtils.isEmpty(pwd)){
                pswd.setError("Password is Empty");
                return;
            }

            fAuth.signInWithEmailAndPassword(usrname,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Faculty Successfully Logged in",Toast.LENGTH_LONG).show();
                        Intent I = new Intent(getApplicationContext(), AddAttendanceSessionActivity.class);
                        startActivity(I);
                    }else{
                        Toast.makeText(MainActivity.this,"Error Occured"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

            //setContentView(R.layout.add_attendance);
        }

    }


}