package com.example.task_list;

import static com.example.task_list.TaskDatabase.DB_NAME;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private int REQUEST_CODE = 1;
    private ExecutorService executorService;

    private List<Task> tasks;

    private TaskDatabase taskDatabase;
    private TaskDAO taskDAO;

    private DbUpdateObserver updateObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasks = new ArrayList<>();

        taskDatabase = Room.databaseBuilder(this, TaskDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        taskDAO=taskDatabase.taskDAO();

        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        updateObserver = new DbUpdateObserver(taskAdapter);

        executorService = new ThreadPoolExecutor(4, 5, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        taskDAO.getAllTask()
                .subscribeOn(Schedulers.from(executorService))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updateObserver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        executorService.shutdown();
    }

    private class DbInsertCompleteObserver implements CompletableObserver {

        private Context context;
        private Task task;

        DbInsertCompleteObserver(Context context, Task task) {
            this.context = context;
            this.task = task;
        }

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onComplete() {
            tasks.add(task);
            taskAdapter.setTasks(tasks);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }
    }

    private class DbUpdateObserver implements FlowableSubscriber<List<Task>> {

        private TaskAdapter adapter;

        DbUpdateObserver(TaskAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onSubscribe(Subscription s) {
            s.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(List<Task> tasks) {
            adapter.setTasks(tasks);
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("Complete");
        }

    }
    public void onClearClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("");
    }

    public void onAddClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String title = editText.getText().toString();
        taskDAO.insert(new Task(title));
    }
}