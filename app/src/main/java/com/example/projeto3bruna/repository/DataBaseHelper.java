package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projeto3bruna.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BancoProjetoBruna";
    private static final Integer DB_VERSION = 2;
    private Context context;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsers = "create table users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userLogin TEXT, password TEXT, email TEXT, phone TEXT);";
        db.execSQL(sqlUsers);

        String sqlToDo = "CREATE TABLE todos (todo_id INTEGER PRIMARY KEY AUTOINCREMENT, todo_title TEXT, todo_status TEXT);";
        //FOREIGN KEY (todo_user_id) REFERENCES users (user_id)
        db.execSQL(sqlToDo);

        String sqlPost = "CREATE TABLE posts (post_id INTEGER PRIMARY KEY AUTOINCREMENT, post_title TEXT, post_body TEXT);";
        //FOREIGN KEY (post_user_id) REFERENCES user (user_id))
        db.execSQL(sqlPost);

        String sqlAlbum = "CREATE TABLE albums (album_id INTEGER PRIMARY KEY AUTOINCREMENT, album_title TEXT);";
        //FOREIGN KEY (album_user_id) REFERENCES user (user_id)
        db.execSQL(sqlAlbum);

        String sqlComment = "CREATE TABLE comments (comment_id INTEGER PRIMARY KEY AUTOINCREMENT, comment_title TEXT, comment_email TEXT, comment_body TEXT);";
        // FOREIGN KEY (comment_post_id) REFERENCES posts (post_id)
        db.execSQL(sqlComment);

        String sqlPhoto = "CREATE TABLE photos (photo_id INTEGER PRIMARY KEY AUTOINCREMENT, photo_title TEXT, photo_url TEXT, photo_thumbnail TEXT);";
        //FOREIGN KEY (album_photo_id) REFERENCES albums (album_id)
        db.execSQL(sqlPhoto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql_upgrade_users = "DROP TABLE if EXISTS users";
        db.execSQL(sql_upgrade_users);

        String sql_upgrade_toDo = "DROP TABLE if EXISTS todos";
        db.execSQL(sql_upgrade_toDo);

        String sql_upgrade_comment = "DROP TABLE if EXISTS comments";
        db.execSQL(sql_upgrade_comment);

        String sql_upgrade_post = "DROP TABLE if EXISTS posts";
        db.execSQL(sql_upgrade_post);

        String sql_upgrade_photo = "DROP TABLE if EXISTS photos";
        db.execSQL(sql_upgrade_photo);

        String sql_upgrade_album = "DROP TABLE if EXISTS albums";
        db.execSQL(sql_upgrade_album);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

}
