package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto3bruna.model.Todo;
import com.example.projeto3bruna.model.User;

import java.util.ArrayList;
import java.util.List;

public class ToDoSQLRepository {

    private final String TAG = "ToDoSQLRepository";
    private static ToDoSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;
    private OnReadyListener onReadyListener;

    public ToDoSQLRepository(Context contexto){
        super();
        this.contexto = contexto;
    }

    public static ToDoSQLRepository getInstance(Context contexto){
        if(instance==null){
            instance = new ToDoSQLRepository(contexto);
        }
        return instance;
    }

    public static ToDoSQLRepository getInstance(Context contexto, OnReadyListener orl){
        if (instance== null){
            instance = new ToDoSQLRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getToDoList().isEmpty()){
            if (orl != null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

    public static ToDoSQLRepository getInstance() {

        return instance;
    }

    public void addToDo(Todo todo){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("todo_id", todo.getTodoId());
        dados.put("todo_title", todo.getTodoTitle());
        dados.put("todo_status", todo.getTodoStatus());
        dados.put("todo_user_id", todo.getUser().getId());

        long newTodo = database.insert("todos", null, dados);

        if(newTodo == -1){
            Toast.makeText(contexto, "Falhou", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contexto, "Usuário adicionado!", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    @SuppressLint("Range")
    public List<Todo> getToDoList(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getReadableDatabase();
        String sql_getTodos = "SELECT * FROM todos;";

        Cursor cursor = database.rawQuery(sql_getTodos, null);
        List<Todo> toDoList = new ArrayList<>();

        while(cursor.moveToNext()){
            Todo todo = new Todo();
            todo.setTodoId(cursor.getInt(cursor.getColumnIndex("todo_id")));
            todo.setTodoTitle(cursor.getString(cursor.getColumnIndex("todo_title")));
            todo.setTodoStatus(cursor.getString(cursor.getColumnIndex("todo_status")));
            toDoList.add(todo);
        }
        return toDoList;
    }

    public void addToDoTest(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getReadableDatabase();

        Todo todo = new Todo(1, "Teste", "Teste");

        ContentValues toDoTeste = new ContentValues();
        toDoTeste.put("todo_id",todo.getTodoId());
        toDoTeste.put("todo_title", todo.getTodoTitle());
        toDoTeste.put("todo_status", todo.getTodoStatus());

        database.insert("todos", null, toDoTeste);
        Log.d(TAG, "addToDoTest: gravação de tarefa teste");
        database.close();
    }

}
