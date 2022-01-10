package com.example.task_list;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    private TextView taskTitle;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        this.taskTitle=itemView.findViewById(R.id.editText);
    }

    public void setTask(Task task){
        taskTitle.setText(task.getTitle());
    }

    public TextView getTaskTitle() {
        return taskTitle;
    }
    public Task getTask(){
        return new Task(taskTitle.toString());
    }
}
