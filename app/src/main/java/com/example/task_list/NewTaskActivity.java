package com.example.task_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.tasklistsql.REPLY";
    private EditText mEditTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        mEditTitleView = findViewById(R.id.edit_task);

        final Button button = findViewById(R.id.button_add);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditTitleView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String title = mEditTitleView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, title);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
