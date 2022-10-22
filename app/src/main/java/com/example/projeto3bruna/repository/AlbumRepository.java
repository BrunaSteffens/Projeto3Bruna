package com.example.projeto3bruna.repository;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projeto3bruna.model.Album;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    private final String TAG = "Album Repository";
    private final List<Album> albums;
    private static AlbumRepository instance;
    private Context context;

    private AlbumRepository(Context context){
        super();
        albums = new ArrayList<>();
        this.context = context;

        RequestQueue albumQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/albums",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                     public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: "+response.length());

                        for(int i=0; i< response.length(); i++){
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: "+json.toString());
                                albums.add(new Album(json.getInt("userId"),
                                        json.getInt("id"), json.getString("title")));
                                //int userId, int albumId, String title

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e(TAG, "onResponse: Inclusão dos álbuns finalizada");
                    }
                },
                new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse (VolleyError error){
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });

        albumQueue.add(jsonArrayRequest);

        Log.e(TAG, "AlbumRepository: Repositório de álbuns lançado");
    }


    public static AlbumRepository getInstance(Context context){
        if (instance == null){
            instance = new AlbumRepository(context);
        }
        return instance;
    }

    public static AlbumRepository getInstance(){
        return instance;
    }

    public List<Album> getAlbums(){
        return albums;
    }


    public Album getAlbumById(int albumId){
        Album ret = null;
        Log.d(TAG, "getAlbumByTitle: "+albums.size());
        for(Album a : albums){
            Log.d(TAG, "getAlbumById: " + albumId+ " ->" +a.getAlbumId());
            if(a.getAlbumId()==albumId){
                ret = a;
            }
        }
        return ret;
    }
}