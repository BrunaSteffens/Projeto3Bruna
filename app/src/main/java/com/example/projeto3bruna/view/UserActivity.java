package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.UserAdapter;
import com.example.projeto3bruna.repository.OnReadyListener;
import com.example.projeto3bruna.repository.UserSQLRepository;

import android.os.Bundle;
import android.util.Log;

public class UserActivity extends AppCompatActivity {
    private final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Log.d(TAG, "onCreate: Construindo o recycler view dos usuários");

        RecyclerView rv = findViewById(R.id.recyclerUser);
        UserAdapter adapter = new UserAdapter(UserSQLRepository.getInstance().getUsers());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

    }
}