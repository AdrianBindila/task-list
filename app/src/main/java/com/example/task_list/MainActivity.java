package com.example.task_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TaskDatabase db;
    TaskDAO taskDAO;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "task-database").build();
        taskDAO = db.taskDAO();
        tasks = taskDAO.getAll();


    }
}