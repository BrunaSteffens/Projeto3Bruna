package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.CommentsAdapter;
import com.example.projeto3bruna.repository.CommentSQLRepository;
import com.example.projeto3bruna.repository.CommentsRepository;
import android.os.Bundle;

public class CommentActivity extends AppCompatActivity {
    private static final String TAG = "CommentsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        CommentSQLRepository.getInstance(this).addCommentTest();

        RecyclerView rv = findViewById(R.id.recyclerViewComments);
        CommentsAdapter adapter = new CommentsAdapter(CommentSQLRepository.getInstance().getComments());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }
}