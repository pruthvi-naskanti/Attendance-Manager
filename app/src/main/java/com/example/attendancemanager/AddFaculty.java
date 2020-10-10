package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFaculty extends AppCompatActivity {

    Button B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_faculty);
        B=findViewById(R.id.RegisterButton);
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
