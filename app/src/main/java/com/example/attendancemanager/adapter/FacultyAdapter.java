package com.example.attendancemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.models.*;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private final List<Faculty> data;

    public FacultyAdapter(List<Faculty> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_faculty, parent, false);
        return new FacultyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
       Faculty item = data.get(position);
holder.name.setText("Name : "+item.getFirstName()+"."+item.getLastName() + " Username :"+item.getUsername()+ " Password: "+item.getPassword());    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class FacultyViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemFaculty);
        }
    }

}
