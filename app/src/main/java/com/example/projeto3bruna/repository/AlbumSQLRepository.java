package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto3bruna.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumSQLRepository {

    private final String TAG = "Album SQL Repository";
    private static AlbumSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public AlbumSQLRepository(Context contexto){
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static AlbumSQLRepository getInstance(Context contexto){
        if (instance == null) {
            instance = new AlbumSQLRepository(contexto);
        }
        return instance;
    }

    public static AlbumSQLRepository getInstance(){
        return instance;
    }

    public void addAlbum(Album album){
        //album_id INTEGER PRIMARY KEY AUTOINCREMENT, album_title TEXT
        String sql = "insert into albums (album_id, album_title) values (?, ?);";
        Object[] args = {album.getAlbumId(), album.getTitle()};
        database.execSQL(sql, args);
    }

    public void updateAlbum(Album album){
        String sql = "update albums set album_id=?, album_title=?;";
        Object[] args = {album.getAlbumId(), album.getTitle()};
        database.execSQL(sql, args);
    }

    public void deleteAlbum(Album album){
        //album_id INTEGER PRIMARY KEY AUTOINCREMENT, album_title TEXT
        String sql = "delete from albums where album_id=?;";
        Object[] args = {album.getAlbumId()};
        database.execSQL(sql, args);
    }

    public List<Album> getAlbums(){
        String sql_getAlbums = "SELECT * FROM albums;";
        Cursor cursor = database.rawQuery(sql_getAlbums, null);
        cursor.moveToFirst();
        List<Album> albumList = new ArrayList<>();

        do{
            albumList.add(albumFromCursor(cursor));
        } while (cursor.moveToNext());
        return albumList;
    }

    public void addAlbumTest(){
            String sql = "insert into albums (album_title) values ('Album Teste');";
            database.execSQL(sql);
            Log.d(TAG, "addAlbumTest: gravação de album teste");
    }

    private Album albumFromCursor(Cursor cursor){
        Album album = new Album(
                cursor.getInt(0),
                cursor.getString(1));
        return album;
    }
}
