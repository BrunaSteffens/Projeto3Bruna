package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.TodosAdapter;
import com.example.projeto3bruna.repository.ToDoSQLRepository;
import com.example.projeto3bruna.repository.TodosRepository;

import android.os.Bundle;
import android.util.Log;

public class TodoActivity extends AppCompatActivity {
    private static final String TAG = "TodosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        Log.d(TAG, "onCreate: Iniciando a activity das tarefas");

        ToDoSQLRepository.getInstance(this).addToDoTest();

        RecyclerView rv = findViewById(R.id.recyclerTodos);
        TodosAdapter adapter = new TodosAdapter(ToDoSQLRepository.getInstance().getToDoList());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }
}