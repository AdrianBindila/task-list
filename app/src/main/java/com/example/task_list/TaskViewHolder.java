package com.example.task_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    private TextView taskTitle;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        this.taskTitle=itemView.findViewById(R.id.textView);
    }
    public void bind(String text) {
        taskTitle.setText(text);
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(view);
    }
}
