package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto3bruna.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSQLRepository {
    private final String TAG = "UserSQLRepository";
    private static UserSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public UserSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static UserSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserSQLRepository(contexto);
        }
        return instance;
    }

    public static UserSQLRepository getInstance() {
        return instance;
    }

    public void addUser(User user) {
        String sql = "insert into users (id, name, userLogin, password) values(?, ?, ?, ?);";
        Object[] args = {user.getId(), user.getName(), user.getUserLogin(), user.getPassword()};
        database.execSQL(sql, args);
    }

    public void updateUser(User user){
        String sql = "update users set name = ?, userLogin = ?, password = ?;";
        Object[] args = {user.getName(), user.getUserLogin(), user.getPassword()};
        database.execSQL(sql);
    }

    public void deleteUser(User user){
        String sql = "delete from users where userLogin = ?;";
        Object[] args = { user.getUserLogin()};
        database.execSQL(sql);
    }

        public List<User> getUsers() {
        String sql_getUsers = "SELECT * FROM users;";
        Cursor cursor = database.rawQuery(sql_getUsers, null);
        cursor.moveToFirst();
        List<User> userList = new ArrayList<>();

        do{
            userList.add(userFromCursor(cursor));
        }while(cursor.moveToNext());
        return userList;
    }

    public void addUserTest(){
            String sql = "insert into users (name, userLogin, password, email, phone) values ('Bruna', 'bruna', '1234', 'bruna@', '51999999999');";
            database.execSQL(sql);
            Log.d(TAG, "addUserTest: gravação de usuário teste");
    }

    public User getUserByUserLogin(String login) {
        String sql_getUserByLogin = "SELECT * FROM users WHERE userLogin=?;";
        String [] args = {login};
        Cursor cursor = database.rawQuery(sql_getUserByLogin, args );

        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    public User getUserById(int id) {
        String sql_getUserByLogin = "SELECT * FROM users WHERE user_id=?;";
        String [] args = {""+id};
        Cursor cursor = database.rawQuery(sql_getUserByLogin, args );

        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    private User userFromCursor(Cursor cursor) {
        //int id, String name, String userLogin, String password, String email, String phone
        User user = new User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));
        return user;
    }
}