package com.example.task_list;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
@Entity
public class Task {
    private enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    @PrimaryKey
    private int uid;

    private String title;
    private String description;
    private Priority priority;
    private Date creationDate;
    private Date dueDate;


    public Task() {
        title = "Task title";
        description = "description";
        priority = Priority.LOW;
        creationDate = Calendar.getInstance().getTime();
        dueDate = Calendar.getInstance().getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void print() {
        System.out.println("=============");
        System.out.println(title);
        System.out.println(description);
        System.out.println(priority);
        System.out.println(creationDate);
        System.out.println(dueDate);
    }
}
