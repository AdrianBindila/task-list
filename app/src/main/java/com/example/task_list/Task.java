package com.example.task_list;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.UUID;

@Entity
public class Task {
    @PrimaryKey
    private int uid;

    private String title;

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
