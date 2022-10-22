package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.example.projeto3bruna.model.Comment;
import com.example.projeto3bruna.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentSQLRepository {
    private static CommentSQLRepository instance;
    private Context contexto;
    private static final String TAG = "CommentSQLRepository";
    private SQLiteDatabase database;

    public CommentSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static CommentSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new CommentSQLRepository(contexto);
        }
        return instance;
    }

    public static CommentSQLRepository getInstance() {
        return instance;
    }

    public void addComment(Comment comment) {
        //comment_title TEXT, comment_email TEXT, comment_body TEXT
        String sql = "insert into comments (comment_id, comment_title, comment_email, comment_body) values (?, ?, ?, ?);";
        Object[] args = {comment.getCommentId(), comment.getCommentTitle(), comment.getCommentEmail(), comment.getCommentBody()};
        database.execSQL(sql, args);
    }

    public void updateComment(Comment comment){
        String sql = "update comment set comment_title = ?, comment_email = ?, comment_body = ?;";
        Object[] args = {comment.getCommentTitle(), comment.getCommentEmail(), comment.getCommentBody()};
        database.execSQL(sql);
    }

    public void deleteComment(Comment comment){
        String sql = "delete from comment where comment_id = ?;";
        Object[] args = {comment.getCommentId()};
        database.execSQL(sql);
    }

    public List<Comment> getComments() {
        String sql_getComments = "SELECT * FROM comments;";
        Cursor cursor = database.rawQuery(sql_getComments, null);
        cursor.moveToFirst();
        List<Comment> commentList = new ArrayList<>();

        do{
            commentList.add(commentFromCursor(cursor));
        } while(cursor.moveToNext());
        return commentList;
    }

    public void addCommentTest(){
            String sql = "insert into comments (comment_title, comment_email, comment_body) values ('Comentário da Bruna', 'bruna@bruna.com', 'Este é o meu comentário');";
            database.execSQL(sql);
            Log.d(TAG, "addCommentTest: gravação de comentário teste");
    }

    private Comment commentFromCursor(Cursor cursor) {
        Comment comment = new Comment(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return comment;
    }
}
