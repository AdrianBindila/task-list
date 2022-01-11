package com.example.task_list;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDAO mTaskDAO;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        mTaskDAO = db.taskDAO();
        mAllTasks = mTaskDAO.getTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        TaskDatabase.dbWriteExecutor.execute(() -> mTaskDAO.insert(task));
    }

    void delete(Task task){
        TaskDatabase.dbWriteExecutor.execute(()-> mTaskDAO.delete(task));
    }
    void deleteAll(){
        TaskDatabase.dbWriteExecutor.execute(()-> mTaskDAO.deleteAll());
    }
}
