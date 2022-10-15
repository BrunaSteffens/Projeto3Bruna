package com.example.projeto3bruna.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projeto3bruna.model.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodosRepository {
    private final List<Todo> todoList;
    private static TodosRepository instance;
    private Context contexto;
    private final String TAG = "TodosRepository";

    private TodosRepository(Context contexto) {
        super();
        todoList = new ArrayList<>();
        this.contexto = contexto;

        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonArrayRequest jaRequest =
                new JsonArrayRequest(Request.Method.GET,
                        "https://jsonplaceholder.typicode.com/todos",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                response.length();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        ;
                                        JSONObject json = response.getJSONObject(i);
                                        todoList.add(new Todo(json.getInt("userId"), json.getInt("id"), json.getString("title"),
                                                json.getString("completed")));
                                        //(int id, int todoId, String title, String status)
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                 }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse: " + error.getMessage());

                            }
                        });
        queue.add(jaRequest);

    }

    public static TodosRepository getInstance(Context contexto){
        if(instance == null){
            instance = new TodosRepository(contexto);
        }
        return instance;
    }

    public List<Todo> getTodos(){ return todoList; }

    public Todo getTodoById(int id){
        Todo ret = null;
        for(Todo t : todoList){
            if (t.getTodoId() == id){
                ret = t;
            }
        }
        return ret;
    }
}
