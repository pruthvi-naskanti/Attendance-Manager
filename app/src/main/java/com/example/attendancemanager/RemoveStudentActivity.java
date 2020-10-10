package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveStudentActivity extends AppCompatActivity {

    Button REMOVE;
    Button CANCEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_student);
        REMOVE.findViewById(R.id.removebtn);
        CANCEL.findViewById(R.id.cbtn);

        REMOVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(I);
                setContentView(R.layout.home_screen);
                Toast.makeText(getApplicationContext(), "Removed Student", Toast.LENGTH_LONG).show();
            }
        });



    }



}
