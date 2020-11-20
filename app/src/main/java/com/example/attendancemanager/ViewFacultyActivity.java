package com.example.attendancemanager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.adapter.FacultyAdapter;
import com.example.attendancemanager.models.Faculty;
import com.example.attendancemanager.other.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewFacultyActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private FacultyAdapter adapter;
    private final List<Faculty> facultyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_faculty_list);

        db = FirebaseFirestore.getInstance();
        recyclerView =findViewById(R.id.rvViewFaculty);

        getFacultyList();

    }

    private void getFacultyList() {

        db.collection(Constants.faculty)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot item : task.getResult()){
                                facultyList.add((item.toObject(Faculty.class)));
                            }
                            adapter = new FacultyAdapter(facultyList);
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(ViewFacultyActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
