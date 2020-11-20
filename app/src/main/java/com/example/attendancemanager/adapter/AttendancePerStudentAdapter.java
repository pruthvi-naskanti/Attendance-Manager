package com.example.attendancemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.models.Student;
import com.example.attendancemanager.other.Constants;
import com.example.attendancemanager.other.Utils;

import java.util.List;
import java.util.Map;

public class AttendancePerStudentAdapter extends RecyclerView.Adapter<AttendancePerStudentAdapter.AttendanceViewHolder> {

    private final List<Map<String,Object>> data;

    public AttendancePerStudentAdapter(List<Map<String,Object>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_faculty, parent, false);
        return new AttendanceViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        Map<String,Object> dataItem = data.get(position);

        Student item = (Student) dataItem.get(Constants.student);
        boolean availability = (boolean) dataItem.get(Constants.AVAILABILITY);
        String isPresence = "";

        if (availability){
            isPresence = "Present";
        }else{
            isPresence = "Absent";
        }

        holder.name.setText(item.getId() + " " + item.getFirstName() + " " + item.getLastName() + " " + "Present : " + Utils.getTotalPresent(item) +" " +  isPresence);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemFaculty);
        }
    }

}
