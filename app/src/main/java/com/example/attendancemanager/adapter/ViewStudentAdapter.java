package com.example.attendancemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.models.Faculty;
import com.example.attendancemanager.models.Student;

import java.util.List;

public class ViewStudentAdapter extends RecyclerView.Adapter<ViewStudentAdapter.ViewStudentViewHolder> {

    private final List<Student> data;

    public ViewStudentAdapter(List<Student> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_faculty, parent, false);
        return new ViewStudentAdapter.ViewStudentViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStudentViewHolder holder, int position) {
        Student item = data.get(position);
        holder.name.setText("ID: "+item.getId() + ", Name " + item.getFirstName() + "." + item.getLastName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class ViewStudentViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewStudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemFaculty);
        }
    }

}
