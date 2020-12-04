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

import java.util.ArrayList;
import java.util.Map;

public class ViewTotalAttendanceAdapter extends RecyclerView.Adapter {

    private int DATE = 0;
    private int STUDENT = 1;
    private ArrayList<Object> data;

    public ViewTotalAttendanceAdapter(ArrayList<Object> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof String ){
            return DATE;
        }else{
         return STUDENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == DATE){
            View view = inflater.inflate(R.layout.item_faculty, parent, false);
           return new StudentViewHolder(view);
        }else{
            View view = inflater.inflate(R.layout.item_faculty, parent, false);
            return new StudentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == STUDENT){
            Map<String,Object> mapItem = (Map<String,Object>) data.get(position);
            Student item = (Student) mapItem.get(Constants.student);
            boolean isPresence = (boolean) mapItem.get(Constants.AVAILABILITY);
            String availability = "";
            if (isPresence){
                availability = "Present";
            }else{
                availability = "Absent";
            }

            ((StudentViewHolder) holder).textView.setText("ID: "+item.getId() + ", Name: " + item.getFirstName() + "." + item.getLastName() + "->" + availability);
        }else{
            ((StudentViewHolder) holder).textView.setText((String) data.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public StudentViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvItemFaculty);
        }
    }

}
