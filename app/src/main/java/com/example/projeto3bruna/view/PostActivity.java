package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.R;
import android.os.Bundle;

import com.example.projeto3bruna.adapter.PostsAdapter;
import com.example.projeto3bruna.repository.PostsRepository;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = "PostsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        RecyclerView rv = findViewById(R.id.recyclerPosts);
        PostsAdapter adapter = new PostsAdapter(PostsRepository.getInstance(this).getPosts());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }
}