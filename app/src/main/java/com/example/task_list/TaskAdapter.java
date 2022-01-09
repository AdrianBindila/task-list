package com.example.task_list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = "TaskAdapter";

    private Task[] mTasks;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//TODO: add edit dialogue
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            title = (TextView) v.findViewById(R.id.);
        }

        public TextView getTextView() {
            return title;
        }
    }
    public TaskAdapter(Task[] tasks){
        mTasks=tasks;
    }
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(mTasks[position].getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
