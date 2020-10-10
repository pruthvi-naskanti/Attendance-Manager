package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveFacultyActivity extends AppCompatActivity {
    Button Remove;
    Button Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_faculty);
        Remove=findViewById(R.id.facremovebtn);
        Cancel=findViewById(R.id.faccbtn);
        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(I);
                //setContentView(R.layout.home_screen);
                Toast.makeText(getApplicationContext(), "Removed Faculty", Toast.LENGTH_LONG).show();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(I);
                //setContentView(R.layout.home_screen);
            }
        });

    }
}
