package com.example.task_list;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TaskDAO {
    @Insert
    Completable insert(Task... tasks);

    @Delete
    Single<Integer> delete(Task... tasks);

    @Query("DELETE FROM Task WHERE title = :title")
    Single<Integer> deleteByTitle(String title);

    @Query("SELECT title FROM Task WHERE title = :title")
    Flowable<List<String>> getTitle(String title);

    @Query("SELECT * FROM Task")
    Flowable<List<Task>> getAllTask();

//    @Query("SELECT * FROM Task")
//    List<Task> getAll();
//
//    @Query("SELECT * FROM Task WHERE uid IN (:taskIds)")
//    List<Task> loadAllByIds(int[] taskIds);
//
//    @Insert
//    void insertAll(Task... tasks);
//
//    @Insert
//    void insert(Task task);
//
//    @Delete
//    void deleteTasks(Task... tasks);
//
//    @Delete
//    void delete(Task task);
//
//    @Update
//    void updateTasks(Task... tasks);
//
//    @Update
//    void updateTask(Task task);


}
