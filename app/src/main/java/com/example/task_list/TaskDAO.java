package com.example.task_list;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
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
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Insert(onConflict = IGNORE)
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM Task")
    void deleteAll();
}
