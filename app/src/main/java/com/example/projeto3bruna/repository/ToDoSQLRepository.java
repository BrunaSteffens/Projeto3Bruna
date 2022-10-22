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

    public ToDoSQLRepository(Context contexto){
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static ToDoSQLRepository getInstance(Context contexto){
        if(instance==null){
            instance = new ToDoSQLRepository(contexto);
        }
        return instance;
    }

    public static ToDoSQLRepository getInstance() { return instance; }

    public void addToDo(Todo todo){
        //todo_id INTEGER PRIMARY KEY AUTOINCREMENT, todo_title TEXT, todo_status TEXT
        String sql = "insert into todos (todo_id, todo_title, todo_status) values (?, ?, ?);";
        Object[] args = {todo.getTodoId(), todo.getTodoTitle(), todo.getTodoStatus()};
        database.execSQL(sql, args);
    }

    public void updateToDo(Todo todo){
        String sql = "update todos set todo_title=?, todo_status=?;";
        Object[] args = {todo.getTodoTitle(), todo.getTodoStatus()};
        database.execSQL(sql, args);
    }

    public void deleteToDo(Todo todo){
        String sql = "delete from todos where todo_id=?;";
        Object[] args = {todo.getTodoId()};
        database.execSQL(sql, args);
    }

    public List<Todo> getToDoList(){
        String sql_getTodos = "SELECT * FROM todos;";
        Cursor cursor = database.rawQuery(sql_getTodos, null);
        cursor.moveToFirst();
        List<Todo> toDoList = new ArrayList<>();

        do{
            toDoList.add(todoFromCursor(cursor));
        } while(cursor.moveToNext());
        return toDoList;
    }

    public void addToDoTest(){
            String sql = "insert into todos (todo_title, todo_status) values ('Tarefa teste', 'Done');";
            database.execSQL(sql);
            Log.d(TAG, "addToDoTest: gravação de tarefa teste");
    }

    public Todo todoFromCursor(Cursor cursor){
        Todo todo = new Todo(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        return todo;
    }
}
