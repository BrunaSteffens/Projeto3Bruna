package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto3bruna.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostSQLRepository {
    private static final String TAG = "PostSQLRepository";
    private static PostSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public PostSQLRepository(Context contexto){
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static PostSQLRepository getInstance(Context contexto){
        if(instance == null){
            instance = new PostSQLRepository(contexto);
        }
        return instance;
    }

    public static PostSQLRepository getInstance(){ return  instance; }

    public void addPost(Post post){
        // post_title TEXT, post_body TEXT
        String sql = "insert into posts (post_id, post_title, post_body) values (?, ?, ?);";
        Object[] args = {post.getPostId(), post.getPostTitle(), post.getPostBody()};
        database.execSQL(sql, args);
    }

    public void updatePost(Post post){
        String sql = "update posts set post_title=?, post_body=?;";
        Object[] args = {post.getPostTitle(), post.getPostBody()};
        database.execSQL(sql, args);
    }

    public void deletePost(Post post){
        String sql = "delete from posts where post_id=?;";
        Object[] args = {post.getPostId()};
        database.execSQL(sql, args);
    }

    public List<Post> getPosts(){
        String sql_getPosts = "SELECT * FROM posts;";
        Cursor cursor = database.rawQuery(sql_getPosts, null);
        cursor.moveToFirst();
        List<Post> postList = new ArrayList<>();

        do{
            postList.add(postFromCursor(cursor));
        } while (cursor.moveToNext());
        return postList;
    }

    public void addPostTest(){
            String sql = "insert into posts (post_title, post_body) values ('Post da Bru', 'Este é o post teste da Bru!');";
            database.execSQL(sql);
            Log.d(TAG, "addPostTest: gravação de post teste");
    }

    public Post postFromCursor(Cursor cursor){
        Post post = new Post(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        return post;
    }
}
