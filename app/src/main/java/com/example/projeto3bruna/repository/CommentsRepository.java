package com.example.projeto3bruna.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projeto3bruna.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentsRepository {
    private final List<Comment> comments;
    private static CommentsRepository instance;
    private Context contexto;
    private static final String TAG = "CommentsRepository";

    private CommentsRepository(Context contexto){
        super();
        comments = new ArrayList<>();
        this.contexto = contexto;

        RequestQueue queue = Volley.newRequestQueue(contexto);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        "https://jsonplaceholder.typicode.com/comments",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                response.length();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject json = response.getJSONObject(i);
                                        comments.add(new Comment(json.getInt("id"), json.getString("name"),
                                                json.getString("email"), json.getString("body")));
                                        //int commentId, String commentTitle, String commentEmail, String commentBody
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage() );
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public static CommentsRepository getInstance(Context contexto){
        if(instance==null){
            instance = new CommentsRepository(contexto);
        }
        return instance;
    }

    public List<Comment> getComments(){ return comments;}

    public Comment getCommentById(int id){
        Comment ret = null;
        for(Comment c:comments){
            if(c.getCommentId()==id){
                ret = c;
            }
        }
        return ret;
    }
}
