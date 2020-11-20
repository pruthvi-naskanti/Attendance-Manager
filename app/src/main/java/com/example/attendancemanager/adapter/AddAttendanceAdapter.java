package com.example.attendancemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.listener.OnAddAttendance;
import com.example.attendancemanager.models.Student;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAttendanceAdapter extends RecyclerView.Adapter<AddAttendanceAdapter.AddAttendanceViewHolder> {

    private final List<Student> data;
    private final OnAddAttendance onAddAttendance;
    private final String subject;

    public AddAttendanceAdapter(List<Student> data,String subject, OnAddAttendance onAddAttendance) {
        this.data = data;
        this.onAddAttendance = onAddAttendance;
        this.subject = subject;
    }

    @NonNull
    @Override
    public AddAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_add_attendance, parent, false);
        return new AddAttendanceAdapter.AddAttendanceViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddAttendanceViewHolder holder, int position) {
        final Student student = data.get(position);
        holder.name.setText(student.getFirstName() + " " + student.getLastName());

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbAddStudentPresent:
                        onAddAttendance.onPresentAttendanceClick(holder.getAdapterPosition(), student);
                        break;
                    case R.id.rbAddStudentAbsent:
                        onAddAttendance.onAbsentAttendanceClick(holder.getAdapterPosition(), student);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class AddAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        RadioGroup radioGroup;

        public AddAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvAddStudentItem);
            radioGroup = itemView.findViewById(R.id.radioGroup2);
        }
    }
}


