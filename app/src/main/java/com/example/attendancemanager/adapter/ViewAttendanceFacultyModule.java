package com.example.attendancemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.models.Student;

import java.util.List;

public class ViewAttendanceFacultyModule extends RecyclerView.Adapter<ViewAttendanceFacultyModule.ViewAttendanceFacultyModuleViewHolder> {

    private final List<Student> data;

    public ViewAttendanceFacultyModule(List<Student> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewAttendanceFacultyModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_faculty, parent, false);
        return new ViewAttendanceFacultyModuleViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAttendanceFacultyModuleViewHolder holder, int position) {
        Student item = data.get(position);
        holder.name.setText("id : " + item.getId() + " " + item.getFirstName() + " " + item.getLastName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewAttendanceFacultyModuleViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewAttendanceFacultyModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemFaculty);
        }
    }

}
