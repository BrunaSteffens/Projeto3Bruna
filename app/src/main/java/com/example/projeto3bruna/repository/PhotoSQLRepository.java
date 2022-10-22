package com.example.projeto3bruna.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.sax.TextElementListener;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto3bruna.model.Photo;
import com.example.projeto3bruna.model.User;

import java.util.ArrayList;
import java.util.List;

public class PhotoSQLRepository {
    private static final String TAG = "PhotoSQLRepository";
    private static PhotoSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public PhotoSQLRepository(Context contexto){
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static PhotoSQLRepository getInstance(Context contexto){
        if (instance == null) {
            instance = new PhotoSQLRepository(contexto);
        }
        return instance;
    }

    public static PhotoSQLRepository getInstance() {
        return instance;
    }

    public void addPhoto(Photo photo){
        //photo_title TEXT, photo_url TEXT, photo_thumbnail TEXT]
        String sql = "insert into photos (photo_id, photo_title, photo_url, photo_thumbnail) values (?, ?, ?, ?);";
        Object[] args = {photo.getPhotoId(), photo.getPhotoTitle(), photo.getPhotoUrl(), photo.getPhotoThumbnailUrl()};
        database.execSQL(sql, args);
    }

    public void updatePhoto(Photo photo){
        //photo_title TEXT, photo_url TEXT, photo_thumbnail TEXT]
        String sql = "update photos set photo_title=?, photo_url=?, photo_thumbnail=?;";
        Object[] args = {photo.getPhotoTitle(), photo.getPhotoUrl(), photo.getPhotoThumbnailUrl()};
        database.execSQL(sql, args);
    }

    public void deletePhoto(Photo photo){
        //photo_title TEXT, photo_url TEXT, photo_thumbnail TEXT]
        String sql = "delete from photos where photo_id=?;";
        Object[] args = {photo.getPhotoId()};
        database.execSQL(sql, args);
    }

    public List<Photo> getPhotos() {
        String sql_getPhotos = "SELECT * FROM photos;";
        Cursor cursor = database.rawQuery(sql_getPhotos, null);
        cursor.moveToFirst();
        List<Photo> photoList = new ArrayList<>();
        do{
            photoList.add(photoFromCursor(cursor));
        }while (cursor.moveToNext());
        return photoList;
    }

    public void addPhotoTest(){
        String sql = "insert into photos (photo_title, photo_url, photo_thumbnail) values ('Foto teste', 'https://via.placeholder.com/600/24f355', 'https://via.placeholder.com/150/24f355');";
        database.execSQL(sql);
        Log.d(TAG, "addPhotoTest: gravação de foto teste");
    }

    private Photo photoFromCursor(Cursor cursor){
        Photo photo = new Photo(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return photo;
    }
}
