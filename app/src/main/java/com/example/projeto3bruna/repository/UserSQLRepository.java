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
    private OnReadyListener onReadyListener;

    public UserSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
    }

    public static UserSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserSQLRepository(contexto);
        }
        return instance;
    }

    public static UserSQLRepository getInstance(Context contexto, OnReadyListener orl){
        if (instance== null){
            instance = new UserSQLRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getUsers().isEmpty()){
            if (orl != null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

    public static UserSQLRepository getInstance() {
        return instance;
    }

    public SQLiteDatabase getDatabase(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
        return database;
    }

    public void addUser(User user) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("name", user.getName());
        dados.put("userLogin", user.getUserLogin());
        dados.put("password", user.getPassword());
        dados.put("email", user.getEmail());
        dados.put("phone", user.getPhone());

        long newUser = database.insert("users", null, dados);

        if(newUser == -1){
            Toast.makeText(contexto, "Falhou", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contexto, "Usuário adicionado!", Toast.LENGTH_SHORT).show();
        }

        database.close();

    }

    @SuppressLint("Range")
    public List<User> getUsers() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getReadableDatabase();
        String sql_getUsers = "SELECT * FROM users;";


        Cursor cursor = database.rawQuery(sql_getUsers, null);
        List<User> userList = new ArrayList<>();

        while(cursor.moveToNext()){
            User user = new User();
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setUserLogin(cursor.getString(cursor.getColumnIndex("userLogin")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            userList.add(user);

        }
        return userList;
    }

    public void addUserTest(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();

        User u = new User(100, "Bruna", "bruna", "1234", "bruna@", "51999999999");

        ContentValues userTeste = new ContentValues();
        userTeste.put("name", u.getName());
        userTeste.put("userLogin", u.getUserLogin());
        userTeste.put("password", u.getPassword());
        userTeste.put("email", u.getEmail());
        userTeste.put("phone", u.getPhone());

        database.insert("users", null, userTeste);
        Log.d(TAG, "addUserTest: gravação de usuário teste");
        database.close();
    }


    @SuppressLint("Range")
    public User getUserByUserLogin(String login) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getReadableDatabase();
        User u = new User();

        String sql_getUserByLogin = "SELECT * FROM users WHERE userLogin=?;";
        String [] args = {login};

        Cursor cursor = database.rawQuery(sql_getUserByLogin, args );

        while(cursor.moveToNext()){
            u.setName(cursor.getString(cursor.getColumnIndex("name")));
            u.setUserLogin(cursor.getString(cursor.getColumnIndex("userLogin")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            }
        return u;
    }
}