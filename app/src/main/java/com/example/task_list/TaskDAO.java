package com.example.task_list;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE uid IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);

    @Insert
    void insertAll(Task... tasks);

    @Insert
    void insert(Task task);

    @Delete
    void deleteTasks(Task... tasks);

    @Delete
    void delete(Task task);

    @Update
    void updateTasks(Task... tasks);

    @Update
    void updateTask(Task task);


}
