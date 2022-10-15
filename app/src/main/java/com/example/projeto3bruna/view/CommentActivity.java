package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.CommentsAdapter;
import com.example.projeto3bruna.repository.CommentsRepository;
import android.os.Bundle;

public class CommentActivity extends AppCompatActivity {
    private static final String TAG = "CommentsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        RecyclerView rv = findViewById(R.id.recyclerViewComments);
        CommentsAdapter adapter = new CommentsAdapter(CommentsRepository.getInstance(this).getComments());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }
}