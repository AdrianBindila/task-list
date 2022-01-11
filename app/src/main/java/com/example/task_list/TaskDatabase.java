package com.example.task_list;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
abstract class TaskDatabase extends RoomDatabase {
    abstract TaskDAO taskDAO();

    private static volatile TaskDatabase INSTANCE;
    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(4);

    static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sTaskDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sTaskDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dbWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TaskDAO dao = INSTANCE.taskDAO();
                dao.deleteAll();

                Task task = new Task("Hello");
                dao.insert(task);
                task = new Task("World");
                dao.insert(task);
            });
        }
    };
}
